package ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview.albums

import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity

interface OnAlbumItemClickListener {
    fun onClick(albumEntity: AlbumEntity)
}