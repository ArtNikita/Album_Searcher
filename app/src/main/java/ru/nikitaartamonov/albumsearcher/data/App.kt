package ru.nikitaartamonov.albumsearcher.data

import android.app.Application
import android.content.Context
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import ru.nikitaartamonov.albumsearcher.domain.AlbumsRepo
import ru.nikitaartamonov.albumsearcher.impl.AlbumsRepoImpl

class App : Application() {
    var currentAlbumsSearchResult: AlbumsRepo = AlbumsRepoImpl()
}

fun AppCompatActivity.hideStatusbar() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    this.window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}

fun Context.getApp(): App = applicationContext as App