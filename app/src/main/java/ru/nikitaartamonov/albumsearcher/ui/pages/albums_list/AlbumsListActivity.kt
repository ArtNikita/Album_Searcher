package ru.nikitaartamonov.albumsearcher.ui.pages.albums_list

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import ru.nikitaartamonov.albumsearcher.data.getApp
import ru.nikitaartamonov.albumsearcher.data.hideStatusbar
import ru.nikitaartamonov.albumsearcher.databinding.ActivityAlbumsListBinding
import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity
import ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview.AlbumsRecyclerViewAdapter
import ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview.OnAlbumItemClickListener

class AlbumsListActivity : AppCompatActivity() {
    private val viewModel: AlbumsListContract.ViewModel by viewModels<AlbumsListViewModel>()
    private lateinit var binding: ActivityAlbumsListBinding
    private val adapter = AlbumsRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusbar()
        binding = ActivityAlbumsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.albumsListRecyclerView.layoutManager =
            GridLayoutManager(
                this,
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT
                    else -> HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT
                }
            )
        adapter.listener = object : OnAlbumItemClickListener {
            override fun onClick(albumEntity: AlbumEntity) {
                viewModel.onAlbumItemClicked(albumEntity)
            }
        }
        binding.albumsListRecyclerView.adapter = adapter
        adapter.setData(getApp().currentAlbumsSearchResult)
    }

    companion object {
        private const val VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT = 3
        private const val HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT = 5
    }
}