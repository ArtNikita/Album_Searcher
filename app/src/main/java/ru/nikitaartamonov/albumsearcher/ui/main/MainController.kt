package ru.nikitaartamonov.albumsearcher.ui.main

class MainController {
    interface ViewModel {
        fun onSearchButtonPressed(textToSearch: String)
    }
}