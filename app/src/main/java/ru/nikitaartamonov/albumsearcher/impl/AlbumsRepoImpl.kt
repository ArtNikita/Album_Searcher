package ru.nikitaartamonov.albumsearcher.impl

import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity
import ru.nikitaartamonov.albumsearcher.domain.AlbumsRepo
import java.util.*

class AlbumsRepoImpl(override val albumsSet: TreeSet<AlbumEntity> = TreeSet()) : AlbumsRepo {

    override val size: Int
        get() = albumsSet.size

    override fun addAlbum(albumEntity: AlbumEntity): Boolean {
        val tempSize = size
        albumsSet.add(albumEntity)
        return tempSize != size
    }

    override fun deleteAlbum(id: Long): Boolean {
        val tempSize = size
        albumsSet.remove(getAlbum(id))
        return tempSize != size
    }

    override fun getAlbum(id: Long): AlbumEntity? = albumsSet.find { it.collectionId == id }
}