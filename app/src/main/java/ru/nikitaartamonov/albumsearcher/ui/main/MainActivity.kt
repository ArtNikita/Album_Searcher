package ru.nikitaartamonov.albumsearcher.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.nikitaartamonov.albumsearcher.R
import ru.nikitaartamonov.albumsearcher.data.getApp
import ru.nikitaartamonov.albumsearcher.data.hideStatusbar
import ru.nikitaartamonov.albumsearcher.databinding.ActivityMainBinding
import ru.nikitaartamonov.albumsearcher.ui.pages.albums_list.AlbumsListActivity

class MainActivity : AppCompatActivity() {
    private val viewModel: MainController.ViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusbar()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initViewModel()
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
}