package ru.nikitaartamonov.albumsearcher.domain

class ApiContract {
    companion object {
        private const val uriStart = "https://itunes.apple.com/search"
        private const val country = "ru"
        private const val entity = "album"

        fun getURI(textToSearch: String) =
            "$uriStart?term=$textToSearch&entity=$entity&country=$country"
    }
}