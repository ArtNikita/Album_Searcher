package ru.nikitaartamonov.albumsearcher.impl

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikitaartamonov.albumsearcher.data.retrofit.ITunesAlbumsApi
import ru.nikitaartamonov.albumsearcher.domain.*
import java.util.*

private const val BASE_URL = "https://itunes.apple.com/"
private const val ALBUM_ENTITY_TYPE = "album"
private const val SONG_ENTITY_TYPE = "song"
private const val COUNTRY = "ru"

class ServerAlbumsLoaderImpl : ServerAlbumsLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var api: ITunesAlbumsApi = retrofit.create(ITunesAlbumsApi::class.java)

    override fun loadAlbumsAsync(
        textToSearch: String,
        callback: (albumsRepo: AlbumsRepo?) -> Unit
    ) {
        api.loadAlbumsByName(textToSearch, ALBUM_ENTITY_TYPE, COUNTRY)
            .enqueue(object : Callback<AlbumsRequestResult> {
                override fun onResponse(
                    call: Call<AlbumsRequestResult>,
                    response: Response<AlbumsRequestResult>
                ) {
                    callback.invoke(AlbumsRepoImpl(response.body()?.results ?: TreeSet()))
                }

                override fun onFailure(call: Call<AlbumsRequestResult>, t: Throwable) {
                    callback.invoke(null)
                }

            })
    }

    override fun loadSpecificAlbumAsync(
        collectionId: Long,
        callback: (listOfSongs: List<SongEntity>?) -> Unit
    ) {
        api.loadSpecificAlbumById(collectionId, SONG_ENTITY_TYPE, COUNTRY)
            .enqueue(object : Callback<SpecificAlbumRequestResult> {
                override fun onResponse(
                    call: Call<SpecificAlbumRequestResult>,
                    response: Response<SpecificAlbumRequestResult>
                ) {
                    callback.invoke(response.body()?.results ?: emptyList())
                }

                override fun onFailure(call: Call<SpecificAlbumRequestResult>, t: Throwable) {
                    callback.invoke(null)
                }
            })
    }
}