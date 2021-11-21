package ru.nikitaartamonov.albumsearcher.domain

data class SongEntity(
    val trackNumber: Int,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Long
)