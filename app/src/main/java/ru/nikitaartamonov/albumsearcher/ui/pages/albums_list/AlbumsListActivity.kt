package ru.nikitaartamonov.albumsearcher.ui.pages.albums_list

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.nikitaartamonov.albumsearcher.R
import ru.nikitaartamonov.albumsearcher.data.getApp
import ru.nikitaartamonov.albumsearcher.data.hideStatusbar
import ru.nikitaartamonov.albumsearcher.databinding.ActivityAlbumsListBinding
import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity
import ru.nikitaartamonov.albumsearcher.ui.pages.album_description.FragmentAlbumFullDescription
import ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview.albums.AlbumsRecyclerViewAdapter
import ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview.albums.OnAlbumItemClickListener

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
        initViewModel()
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

    private fun initViewModel() {
        viewModel.showDownloadSpecificAlbumErrorLiveData.observe(this) {
            it.getContentIfNotHandled()?.let {
                showSnackbar(R.string.internet_connection_error_snackbar_text)
            }
        }
        viewModel.showSpecificAlbumFullDescriptionLiveData.observe(this) {
            it.getContentIfNotHandled()?.let { albumEntity ->
                showSpecificAlbumFullDescription(albumEntity)
            }
        }
        viewModel.addAlbumToHistoryLiveData.observe(this) {
            it.getContentIfNotHandled()?.let { albumEntity ->
                getApp().albumsHistoryRepo.addAlbum(albumEntity)
            }
        }
        viewModel.showProgressBarLiveData.observe(this) {
            if (it) binding.progressBarFrameLayout.visibility = View.VISIBLE
            else binding.progressBarFrameLayout.visibility = View.GONE
        }
    }

    private fun showSpecificAlbumFullDescription(albumEntity: AlbumEntity) {
        FragmentAlbumFullDescription.newInstance(albumEntity)
            .show(supportFragmentManager, FragmentAlbumFullDescription.TAG)
    }

    fun Context.showSnackbar(stringId: Int) {
        Snackbar.make(
            binding.albumsListRecyclerView,
            getString(stringId),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    companion object {
        private const val VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT = 3
        private const val HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT = 5
    }
}