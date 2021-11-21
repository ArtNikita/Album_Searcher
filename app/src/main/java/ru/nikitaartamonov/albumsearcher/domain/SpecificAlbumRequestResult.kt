package ru.nikitaartamonov.albumsearcher.domain

data class SpecificAlbumRequestResult(
    val resultCount: Int,
    val results: List<SongEntity>
)