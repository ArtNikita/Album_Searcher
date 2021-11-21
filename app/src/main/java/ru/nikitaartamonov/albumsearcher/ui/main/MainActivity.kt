package ru.nikitaartamonov.albumsearcher.ui.main

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.nikitaartamonov.albumsearcher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: MainController.ViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
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
    }

    private fun showDownloadError() {
        Snackbar.make(
            binding.historyRecyclerView,
            "Check your internet connection.",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}