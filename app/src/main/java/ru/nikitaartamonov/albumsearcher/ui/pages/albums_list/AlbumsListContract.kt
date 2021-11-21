package ru.nikitaartamonov.albumsearcher.ui.pages.albums_list

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity
import ru.nikitaartamonov.albumsearcher.domain.Event

class AlbumsListContract {
    interface ViewModel {
        val showDownloadSpecificAlbumErrorLiveData: LiveData<Event<Boolean>>
        val showSpecificAlbumFullDescriptionLiveData: LiveData<Event<AlbumEntity>>

        fun onAlbumItemClicked(albumEntity: AlbumEntity)
    }
}