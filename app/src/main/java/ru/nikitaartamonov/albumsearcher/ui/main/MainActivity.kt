package ru.nikitaartamonov.albumsearcher.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.nikitaartamonov.albumsearcher.R
import ru.nikitaartamonov.albumsearcher.data.getApp
import ru.nikitaartamonov.albumsearcher.data.hideStatusbar
import ru.nikitaartamonov.albumsearcher.databinding.ActivityMainBinding
import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity
import ru.nikitaartamonov.albumsearcher.ui.pages.album_description.FragmentAlbumFullDescription
import ru.nikitaartamonov.albumsearcher.ui.pages.albums_list.AlbumsListActivity
import ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview.albums.AlbumsRecyclerViewAdapter
import ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview.albums.OnAlbumItemClickListener

class MainActivity : AppCompatActivity() {
    private val viewModel: MainContract.ViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private val adapter = AlbumsRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusbar()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initViewModel()
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStartView()
    }

    private fun initRecyclerView() {
        binding.historyRecyclerView.layoutManager =
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
        binding.historyRecyclerView.adapter = adapter
        adapter.setData(getApp().albumsHistoryRepo)
    }

    private fun initViews() {
        binding.searchButton.setOnClickListener {
            viewModel.onSearchButtonPressed(binding.searchEditText.text.toString())
        }
    }

    private fun initViewModel() {
        viewModel.showDownloadErrorLiveData.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                showDownloadError()
            }
        }
        viewModel.hideKeyboardAndClearEditTextFocusLiveData.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                hideKeyboard()
                binding.searchEditText.clearFocus()
            }
        }
        viewModel.showEmptyResultLiveData.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                showEmptyResultSnackbar()
            }
        }
        viewModel.setCurrentAlbumsSearchResultAndStartActivityLiveData.observe(this) {
            it.getContentIfNotHandled()?.let { albumsRepo ->
                getApp().currentAlbumsSearchResult = albumsRepo
                startActivity(Intent(this, AlbumsListActivity::class.java))
            }
        }
        viewModel.showSpecificAlbumFullDescriptionLiveData.observe(this) {
            it.getContentIfNotHandled()?.let { albumEntity ->
                showSpecificAlbumFullDescription(albumEntity)
            }
        }
        viewModel.notifyAdapterLiveData.observe(this) {
            it.getContentIfNotHandled()?.let {
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun showSpecificAlbumFullDescription(albumEntity: AlbumEntity) {
        FragmentAlbumFullDescription.newInstance(albumEntity)
            .show(supportFragmentManager, FragmentAlbumFullDescription.TAG)
    }

    private fun showEmptyResultSnackbar() {
        showSnackbar(R.string.empty_result_snackbar_text)
    }

    private fun showDownloadError() {
        showSnackbar(R.string.internet_connection_error_snackbar_text)
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun Context.showSnackbar(stringId: Int) {
        Snackbar.make(
            binding.historyRecyclerView,
            getString(stringId),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    companion object {
        private const val VERTICAL_RECYCLER_VIEW_COLUMNS_COUNT = 3
        private const val HORIZONTAL_RECYCLER_VIEW_COLUMNS_COUNT = 5
    }
}