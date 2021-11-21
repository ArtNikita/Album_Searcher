package ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.albumsearcher.domain.AlbumsRepo

class AlbumsRecyclerViewAdapter : RecyclerView.Adapter<AlbumsViewHolder>() {
    private lateinit var albumsRepo: AlbumsRepo

    fun setData(albumsRepo: AlbumsRepo){
        this.albumsRepo = albumsRepo
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        return AlbumsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = albumsRepo.size

    private fun getItem(position: Int) = albumsRepo.albumsSet.toTypedArray()[position]
}