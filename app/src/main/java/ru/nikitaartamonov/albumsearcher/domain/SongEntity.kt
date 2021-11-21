package ru.nikitaartamonov.albumsearcher.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SongEntity(
    val trackNumber: Int,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Long
): Parcelable