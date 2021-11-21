package ru.nikitaartamonov.albumsearcher.ui.pages.albums_list

import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity

class AlbumsListViewModel : ViewModel(), AlbumsListContract.ViewModel {
    override fun onAlbumItemClicked(albumEntity: AlbumEntity) {
        TODO("Not yet implemented")
    }
}