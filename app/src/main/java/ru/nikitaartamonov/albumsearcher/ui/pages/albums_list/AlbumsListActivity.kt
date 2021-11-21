package ru.nikitaartamonov.albumsearcher.ui.pages.albums_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.nikitaartamonov.albumsearcher.data.hideStatusbar
import ru.nikitaartamonov.albumsearcher.databinding.ActivityAlbumsListBinding

class AlbumsListActivity : AppCompatActivity() {
    private val viewModel: AlbumsListContract.ViewModel by viewModels<AlbumsListViewModel>()
    private lateinit var binding: ActivityAlbumsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusbar()
        binding = ActivityAlbumsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}