package ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.albumsearcher.databinding.RecyclerViewSongItemBinding
import ru.nikitaartamonov.albumsearcher.domain.SongEntity

class SongViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        RecyclerViewSongItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).root
    ) {
    private val binding = RecyclerViewSongItemBinding.bind(itemView)

    fun bind(songEntity: SongEntity, mainArtistName: String) {
        binding.trackNumberTextView.text = songEntity.trackNumber.toString()
        binding.trackNameTextView.text = songEntity.trackName
        if (mainArtistName != songEntity.artistName)
            binding.artistNameTextView.text = songEntity.artistName
        val min = (songEntity.trackTimeMillis / 1000 / 60).toString()
        var sec = (songEntity.trackTimeMillis / 1000 % 60).toString()
        if (sec.toInt() < 10) sec = "0$sec"
        val time = "$min:$sec"
        binding.trackDurationTextView.text = time
    }
}