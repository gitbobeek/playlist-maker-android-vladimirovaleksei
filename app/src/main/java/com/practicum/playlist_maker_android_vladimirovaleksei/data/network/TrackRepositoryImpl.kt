package com.practicum.playlist_maker_android_vladimirovaleksei.data.network

import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.AppDatabase
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.toDomain
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.toEntity
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.TrackDto
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.TrackSearchRequest
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.TrackSearchResponse
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Track
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.NetworkClient
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TrackRepositoryImpl(
    private val database: AppDatabase
) : TrackRepository {

    private val trackDao = database.trackDao()
    private val networkClient: NetworkClient = NetworkClientImpl()

    override suspend fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TrackSearchRequest(expression))
        if (response.resultCode != 200) {
            throw IOException("Network error")
        }
        val searchResponse = response as? TrackSearchResponse
        return searchResponse?.results.orEmpty().map { mapToTrack(it) }
    }

    private fun mapToTrack(dto: TrackDto): Track {
        val trackTime = dto.trackTimeMillis?.let { millis ->
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millis.toLong())
            val seconds = TimeUnit.MILLISECONDS.toSeconds(millis.toLong()) % 60
            String.format("%d:%02d", minutes, seconds)
        }.orEmpty()

        return Track(
            id = dto.trackId ?: 0L,
            trackName = dto.trackName.orEmpty(),
            artistName = dto.artistName.orEmpty(),
            trackTime = trackTime,
            image = dto.artworkUrl100.orEmpty(),
            favorite = false,
            playlistId = 0L
        )
    }

    override fun getTrackByNameAndArtist(track: Track): Flow<Track?> {
        return trackDao.getByNameAndArtist(track.trackName, track.artistName).map { it?.toDomain() }
    }

    override suspend fun insertTrackToPlaylist(track: Track, playlistId: Long) {
        trackDao.insertTrack(track.copy(playlistId = playlistId).toEntity())
    }

    override suspend fun deleteTrackFromPlaylist(track: Track) {
        trackDao.clearTrackFromPlaylist(track.id)
    }

    override suspend fun updateTrackFavoriteStatus(track: Track, isFavorite: Boolean) {
        trackDao.insertTrack(track.copy(favorite = isFavorite).toEntity())
    }

    override suspend fun deleteTracksByPlaylistId(playlistId: Long) {
        trackDao.deleteTracksByPlaylistId(playlistId)
    }

    override fun getFavoriteTracks(): Flow<List<Track>> {
        return trackDao.getFavoriteTracks().map { list -> list.map { it.toDomain() } }
    }

    override fun getTrackById(trackId: Long): Flow<Track?> {
        return trackDao.getTrackById(trackId).map { it?.toDomain() }
    }

    override suspend fun saveTrack(track: Track) {
        trackDao.insertTrack(track.toEntity())
    }
}