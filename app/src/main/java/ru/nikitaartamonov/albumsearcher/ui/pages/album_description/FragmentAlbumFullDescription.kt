package ru.nikitaartamonov.albumsearcher.ui.pages.album_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.nikitaartamonov.albumsearcher.R
import ru.nikitaartamonov.albumsearcher.databinding.FragmentAlbumFullDescriptionBinding
import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity
import ru.nikitaartamonov.albumsearcher.domain.SongEntity
import ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview.songs.SongsRecyclerViewAdapter
import java.util.concurrent.TimeUnit

class FragmentAlbumFullDescription : BottomSheetDialogFragment() {
    private var _binding: FragmentAlbumFullDescriptionBinding? = null
    private val binding get() = _binding!!
    private var albumEntity: AlbumEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumFullDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumEntity = arguments?.getParcelable(ALBUM_ENTITY_KEY)
        bindViews(albumEntity)
    }

    private fun bindViews(albumEntity: AlbumEntity?) {
        albumEntity?.let {
            setupHeader(it)
            setupSongsRecyclerView(it)
            setupFooter(it)
        }
    }

    private fun setupHeader(albumEntity: AlbumEntity) {
        Glide
            .with(requireContext())
            .load(albumEntity.artworkUrl100)
            .into(binding.albumImageView)
        binding.albumImageView.setBackgroundResource(R.drawable.empty_rectangle)
        binding.albumNameTextView.text = albumEntity.collectionName
        binding.artistNameTextView.text = albumEntity.artistName
        val genreAndYearText =
            "${albumEntity.primaryGenreName} ${albumEntity.releaseDate.split("-")[0]}"
        binding.genreAndYearTextView.text = genreAndYearText
    }

    private fun setupSongsRecyclerView(albumEntity: AlbumEntity) {
        binding.songsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = SongsRecyclerViewAdapter()
        adapter.listOfSongs = albumEntity.listOfSongs
        adapter.mainArtistName = albumEntity.artistName
        binding.songsRecyclerView.adapter = adapter
    }

    private fun setupFooter(albumEntity: AlbumEntity) {
        val date = albumEntity.releaseDate.substring(0, 10)
        val summaryTime = calculateTime(albumEntity.listOfSongs)
        val songs =
            "${requireContext().getString(R.string.songs)}: ${albumEntity.listOfSongs.size - 1}"
        val resultText =
            "$date.\n$songs, ${summaryTime[0]}${
                requireContext().getString(R.string.minutes)
            } ${summaryTime[1]}${
                requireContext().getString(R.string.seconds)
            }.\n${albumEntity.copyright}"
        binding.otherAlbumInfoTextView.text = resultText
    }

    private fun calculateTime(listOfSongs: List<SongEntity>): Array<Int> {
        val sumTimeInMillis = listOfSongs.sumOf {
            it.trackTimeMillis
        }
        val min: Int = (sumTimeInMillis / 1000 / 60).toInt()
        val sec: Int = (sumTimeInMillis / 1000 % 60).toInt()
        return arrayOf(min, sec)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val TAG = "FragmentAlbumFullDescription"
        private const val ALBUM_ENTITY_KEY = "ALBUM_ENTITY_KEY"

        fun newInstance(albumEntity: AlbumEntity): FragmentAlbumFullDescription {
            val fragment = FragmentAlbumFullDescription()
            val bundle = Bundle()
            bundle.putParcelable(ALBUM_ENTITY_KEY, albumEntity)
            fragment.arguments = bundle
            return fragment
        }
    }
}