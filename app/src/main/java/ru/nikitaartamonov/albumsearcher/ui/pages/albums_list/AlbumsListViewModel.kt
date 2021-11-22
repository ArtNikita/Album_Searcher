package ru.nikitaartamonov.albumsearcher.ui.pages.albums_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity
import ru.nikitaartamonov.albumsearcher.domain.Event
import ru.nikitaartamonov.albumsearcher.impl.ServerAlbumsLoaderImpl

class AlbumsListViewModel : ViewModel(), AlbumsListContract.ViewModel {
    private val _showDownloadSpecificAlbumErrorLiveData = MutableLiveData<Event<Boolean>>()
    override val showDownloadSpecificAlbumErrorLiveData = _showDownloadSpecificAlbumErrorLiveData
    private val _showSpecificAlbumFullDescriptionLiveData = MutableLiveData<Event<AlbumEntity>>()
    override val showSpecificAlbumFullDescriptionLiveData =
        _showSpecificAlbumFullDescriptionLiveData
    private val _addAlbumToHistoryLiveData = MutableLiveData<Event<AlbumEntity>>()
    override val addAlbumToHistoryLiveData = _addAlbumToHistoryLiveData
    private val _showProgressBarLiveData = MutableLiveData<Boolean>()
    override val showProgressBarLiveData = _showProgressBarLiveData

    private var albumItemAlreadyClicked = false

    override fun onAlbumItemClicked(albumEntity: AlbumEntity) {
        if (albumItemAlreadyClicked) return
        albumItemAlreadyClicked = true
        _showProgressBarLiveData.postValue(true)
        ServerAlbumsLoaderImpl().loadSpecificAlbumAsync(albumEntity.collectionId) { listOfSongs ->
            if (listOfSongs == null) _showDownloadSpecificAlbumErrorLiveData.postValue(Event(true))
            else {
                albumEntity.listOfSongs = listOfSongs
                _showSpecificAlbumFullDescriptionLiveData.postValue(Event(albumEntity))
                _addAlbumToHistoryLiveData.postValue(Event(albumEntity))
            }
            _showProgressBarLiveData.postValue(false)
            albumItemAlreadyClicked = false
        }
    }
}