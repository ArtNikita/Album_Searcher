package ru.nikitaartamonov.albumsearcher.ui.main

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity
import ru.nikitaartamonov.albumsearcher.domain.AlbumsRepo
import ru.nikitaartamonov.albumsearcher.domain.Event

class MainContract {
    interface ViewModel {
        val showDownloadErrorLiveData: LiveData<Event<Boolean>>
        val showEmptyResultLiveData: LiveData<Event<Boolean>>
        val hideKeyboardAndClearEditTextFocusLiveData: LiveData<Event<Boolean>>
        val setCurrentAlbumsSearchResultAndStartActivityLiveData: LiveData<Event<AlbumsRepo>>
        val showSpecificAlbumFullDescriptionLiveData: LiveData<Event<AlbumEntity>>
        val notifyAdapterLiveData: LiveData<Event<Boolean>>

        fun onSearchButtonPressed(textToSearch: String)
        fun onAlbumItemClicked(albumEntity: AlbumEntity)
        fun onStartView()
    }
}