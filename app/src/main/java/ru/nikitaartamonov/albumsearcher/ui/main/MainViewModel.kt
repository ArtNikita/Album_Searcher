package ru.nikitaartamonov.albumsearcher.ui.main

import androidx.lifecycle.ViewModel
import ru.nikitaartamonov.albumsearcher.impl.ServerAlbumsLoaderImpl

class MainViewModel : ViewModel(), MainController.ViewModel {

    private val serverAlbumsLoader = ServerAlbumsLoaderImpl()

    override fun onSearchButtonPressed(textToSearch: String) {
        val input = textToSearch.trim()
        if (input.isEmpty()) return
        serverAlbumsLoader.loadAlbumsAsync(input) {
            //todo
        }
    }
}