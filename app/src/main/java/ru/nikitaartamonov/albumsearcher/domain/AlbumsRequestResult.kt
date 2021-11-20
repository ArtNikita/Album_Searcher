package ru.nikitaartamonov.albumsearcher.domain

import java.util.*

data class AlbumsRequestResult(
    val resultCount: Int,
    val results: TreeSet<AlbumEntity>
)

