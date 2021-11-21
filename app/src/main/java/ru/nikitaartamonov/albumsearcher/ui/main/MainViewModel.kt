package ru.nikitaartamonov.albumsearcher.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.albumsearcher.domain.AlbumsRepo
import ru.nikitaartamonov.albumsearcher.domain.Event
import ru.nikitaartamonov.albumsearcher.impl.ServerAlbumsLoaderImpl

class MainViewModel : ViewModel(), MainController.ViewModel {

    private val serverAlbumsLoader = ServerAlbumsLoaderImpl()

    private val _showDownloadErrorLiveData = MutableLiveData<Event<Boolean>>()
    override val showDownloadErrorLiveData = _showDownloadErrorLiveData
    private val _hideKeyboardAndClearEditTextFocusLiveData = MutableLiveData<Event<Boolean>>()
    override val hideKeyboardAndClearEditTextFocusLiveData =
        _hideKeyboardAndClearEditTextFocusLiveData
    private val _showEmptyResultLiveData = MutableLiveData<Event<Boolean>>()
    override val showEmptyResultLiveData = _showEmptyResultLiveData
    private val _setCurrentAlbumsSearchResultAndStartActivityLiveData =
        MutableLiveData<Event<AlbumsRepo>>()
    override val setCurrentAlbumsSearchResultAndStartActivityLiveData =
        _setCurrentAlbumsSearchResultAndStartActivityLiveData

    override fun onSearchButtonPressed(textToSearch: String) {
        _hideKeyboardAndClearEditTextFocusLiveData.postValue(Event(true))
        val input = textToSearch.trim()
        if (input.isEmpty()) return
        serverAlbumsLoader.loadAlbumsAsync(input) { albumsRepo ->
            if (albumsRepo == null) {
                _showDownloadErrorLiveData.postValue(Event(true))
            } else {
                if (albumsRepo.size == 0) _showEmptyResultLiveData.postValue(Event(true))
                else {
                    _setCurrentAlbumsSearchResultAndStartActivityLiveData.value = Event(albumsRepo)
                }
            }
        }
    }
}