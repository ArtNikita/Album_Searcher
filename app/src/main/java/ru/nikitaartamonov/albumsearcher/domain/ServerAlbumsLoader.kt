package ru.nikitaartamonov.albumsearcher.domain

interface ServerAlbumsLoader {

    fun loadAlbumsAsync(textToSearch: String, callback: (albumsRepo: AlbumsRepo?) -> Unit)
    fun loadSpecificAlbumAsync(collectionId: Long, callback: (listOfSongs: List<SongEntity>?) -> Unit)
}