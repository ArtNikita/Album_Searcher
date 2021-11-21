package ru.nikitaartamonov.albumsearcher.ui.pages.albums_list

import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity

class AlbumsListContract {
    interface ViewModel{
        fun onAlbumItemClicked(albumEntity: AlbumEntity)
    }
}