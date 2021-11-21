package ru.nikitaartamonov.albumsearcher.ui.main

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.albumsearcher.domain.AlbumsRepo
import ru.nikitaartamonov.albumsearcher.domain.Event

class MainController {
    interface ViewModel {
        val showDownloadErrorLiveData: LiveData<Event<Boolean>>
        val showEmptyResultLiveData: LiveData<Event<Boolean>>
        val hideKeyboardAndClearEditTextFocusLiveData: LiveData<Event<Boolean>>
        val setCurrentAlbumsSearchResultAndStartActivityLiveData: LiveData<Event<AlbumsRepo>>

        fun onSearchButtonPressed(textToSearch: String)
    }
}