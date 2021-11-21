package ru.nikitaartamonov.albumsearcher.ui.pages.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.nikitaartamonov.albumsearcher.R
import ru.nikitaartamonov.albumsearcher.databinding.RecyclerViewAlbumItemBinding
import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity

class AlbumsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    RecyclerViewAlbumItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    ).root
) {

    private val binding = RecyclerViewAlbumItemBinding.bind(itemView)
    private lateinit var albumEntity: AlbumEntity

    fun bind(albumEntity: AlbumEntity) {
        this.albumEntity = albumEntity
        binding.albumItemAlbumNameTextView.text = albumEntity.collectionName
        binding.albumItemArtistNameTextView.text = albumEntity.artistName
        Glide
            .with(itemView.context)
            .load(albumEntity.artworkUrl100)
            .into(binding.albumItemImageView)
        binding.albumItemImageView.setBackgroundResource(R.drawable.empty_rectangle)
    }
}