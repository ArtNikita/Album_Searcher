package ru.nikitaartamonov.albumsearcher.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumEntity(
    val collectionId: Long,
    val artistName: String,
    val collectionName: String,
    val artworkUrl100: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val copyright: String,
    var listOfSongs: List<SongEntity> = emptyList()
) : Comparable<AlbumEntity>, Parcelable {
    override fun compareTo(other: AlbumEntity) =
        this.collectionName.compareTo(other.collectionName)
}