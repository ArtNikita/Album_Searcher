package ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview

import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity

interface OnAlbumItemClickListener {
    fun onClick(albumEntity: AlbumEntity)
}