package ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview.songs

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.albumsearcher.domain.SongEntity

class SongsRecyclerViewAdapter : RecyclerView.Adapter<SongViewHolder>() {
    lateinit var listOfSongs: List<SongEntity>
    lateinit var mainArtistName: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongViewHolder(parent)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(getSong(position), mainArtistName)
    }

    override fun getItemCount() = listOfSongs.size - 1

    private fun getSong(position: Int) = listOfSongs.get(position + 1)
}