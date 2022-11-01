package com.jommaa.albums.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jommaa.albums.databinding.DetailsFragmentBinding
import com.jommaa.albums.viewmodel.MainViewModel
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels()
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        binding.albumTitle.text = vm.selectedAlbum.title
        binding.albumUrl.text = vm.selectedAlbum.url
        Picasso.get().load(vm.selectedAlbum.url).into(binding.albumIcon)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}