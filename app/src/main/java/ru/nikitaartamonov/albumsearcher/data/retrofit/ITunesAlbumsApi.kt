package ru.nikitaartamonov.albumsearcher.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nikitaartamonov.albumsearcher.domain.AlbumsRequestResult

interface ITunesAlbumsApi {
    @GET("/search")
    fun loadAlbumsByName(
        @Query("term") textToSearch: String,
        @Query("entity") entityType: String,
        @Query("country") country: String
    ): Call<AlbumsRequestResult>
}