package ru.nikitaartamonov.albumsearcher.domain

interface AlbumsRepo {
    val albumsSet: Set<AlbumEntity>
    val size: Int
    fun addAlbum(albumEntity: AlbumEntity): Boolean
    fun deleteAlbum(id: Long): Boolean
    fun getAlbum(id: Long): AlbumEntity?
}