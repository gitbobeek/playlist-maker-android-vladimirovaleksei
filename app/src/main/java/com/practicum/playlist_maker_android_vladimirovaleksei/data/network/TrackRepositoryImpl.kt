package com.practicum.playlist_maker_android_vladimirovaleksei.data.network

import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Track
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import kotlinx.coroutines.delay

class TrackRepositoryImpl() : TrackRepository {

    override suspend fun searchTracks(expression: String): List<Track> {
        delay(1000) // Имитируем запрос к серверу
        return listTracks.filter { it.trackName.lowercase().contains(expression.lowercase()) }
    }

    val listTracks = listOf(
        Track(
            trackName = "Владивосток 2000",
            artistName = "Мумий Троль",
            trackTime = "2:38",
            image = "",
            favorite = false,
            playlistId = 0
        ),
        Track(
            trackName = "Группа крови",
            artistName = "Кино",
            trackTime = "4:10",
            image = "",
            favorite = false,
            playlistId = 0
        ),
        Track(
            trackName = "Кукушка",
            artistName = "Кино",
            trackTime = "4:13",
            image = "",
            favorite = false,
            playlistId = 0
        ),
        Track(
            trackName = "Всё как у людей",
            artistName = "Зодиак",
            trackTime = "3:25",
            image = "",
            favorite = false,
            playlistId = 0
        ),
        Track(
            trackName = "Моя игра",
            artistName = "Скорпион",
            trackTime = "3:47",
            image = "",
            favorite = false,
            playlistId = 0
        ),
        Track(
            trackName = "Лондон",
            artistName = "Ёлка",
            trackTime = "3:59",
            image = "",
            favorite = false,
            playlistId = 0
        ),
        Track(
            trackName = "Крым",
            artistName = "Серега",
            trackTime = "4:05",
            image = "",
            favorite = false,
            playlistId = 0
        ),
        Track(
            trackName = "О Боже, какой мужчина",
            artistName = "Лариса Долина",
            trackTime = "3:30",
            image = "",
            favorite = false,
            playlistId = 0
        ),
        Track(
            trackName = "Я свободен",
            artistName = "Кипелов",
            trackTime = "4:45",
            image = "",
            favorite = false,
            playlistId = 0
        ),
        Track(
            trackName = "Чёрный бумер",
            artistName = "Серега",
            trackTime = "4:01",
            image = "",
            favorite = false,
            playlistId = 0
        )
    )
}