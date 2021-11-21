package ru.nikitaartamonov.albumsearcher.ui.main

import androidx.lifecycle.LiveData
import ru.nikitaartamonov.albumsearcher.domain.Event

class MainController {
    interface ViewModel {
        val showDownloadErrorLiveData: LiveData<Event<Boolean>>
        val showEmptyResultLiveData: LiveData<Event<Boolean>>
        val hideKeyboardAndClearEditTextFocusLiveData: LiveData<Event<Boolean>>

        fun onSearchButtonPressed(textToSearch: String)
    }
}