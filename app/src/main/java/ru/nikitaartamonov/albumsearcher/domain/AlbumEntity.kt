package ru.nikitaartamonov.albumsearcher.domain

data class AlbumEntity(
    val collectionId: Long,
    val artistName: String,
    val collectionName: String,
    val artworkUrl100: String
) : Comparable<AlbumEntity> {
    override fun compareTo(other: AlbumEntity) =
        this.collectionName.compareTo(other.collectionName)
}