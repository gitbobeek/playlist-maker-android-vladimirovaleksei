package com.practicum.playlist_maker_android_vladimirovaleksei.data.db

import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.entity.PlaylistWithTracks
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.entity.TrackEntity
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Playlist
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Track

fun PlaylistWithTracks.toDomain(): Playlist {
    return Playlist(
        id = playlist.id,
        name = playlist.name,
        description = playlist.description,
        coverImageUri = playlist.coverImageUri,
        tracks = tracks.map { it.toDomain() }
    )
}

fun TrackEntity.toDomain(): Track {
    return Track(
        id = trackId,
        trackName = trackName,
        artistName = artistName,
        trackTime = trackTime,
        image = artworkUrl100,
        favorite = favorite,
        playlistId = playlistId
    )
}

fun Track.toEntity(): TrackEntity {
    return TrackEntity(
        trackId = id,
        trackName = trackName,
        artistName = artistName,
        trackTime = trackTime,
        artworkUrl100 = image,
        favorite = favorite,
        playlistId = playlistId
    )
}

