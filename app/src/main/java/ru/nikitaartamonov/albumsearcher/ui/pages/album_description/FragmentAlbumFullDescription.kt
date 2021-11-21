package ru.nikitaartamonov.albumsearcher.ui.pages.album_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.nikitaartamonov.albumsearcher.databinding.FragmentAlbumFullDescriptionBinding
import ru.nikitaartamonov.albumsearcher.domain.AlbumEntity

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
            //todo
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    
    companion object {
        const val TAG = "FragmentAlbumFullDescription"
        private const val ALBUM_ENTITY_KEY = "ALBUM_ENTITY_KEY"

        fun newInstance(albumEntity: AlbumEntity): FragmentAlbumFullDescription{
            val fragment = FragmentAlbumFullDescription()
            val bundle = Bundle()
            bundle.putParcelable(ALBUM_ENTITY_KEY, albumEntity)
            fragment.arguments = bundle
            return fragment
        }
    }
}