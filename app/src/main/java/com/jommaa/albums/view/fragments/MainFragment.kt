package com.jommaa.albums.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jommaa.albums.R
import com.jommaa.albums.databinding.MainFragmentBinding
import com.jommaa.albums.view.adapters.AlbumsListAdapter
import com.jommaa.albums.view.adapters.OnClickListener
import com.jommaa.albums.viewmodel.MainViewModel
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.entities.Album
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val vm: MainViewModel by activityViewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerAlbumsList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = AlbumsListAdapter(mutableListOf<Album>(), OnClickListener { album ->
                vm.selectedAlbum = album
                findNavController().navigate(R.id.action_go_to_details_page)
            })
        }
        binding.retryButton.setOnClickListener {
            vm.fetchAlbums()
        }
        vm.getAlbums().observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Loading -> {
                    binding.textError.visibility = View.GONE
                    binding.retryButton.visibility = View.GONE
                    binding.progress.visibility = View.VISIBLE
                }
                is DataResult.Success -> {
                    binding.progress.visibility = View.GONE
                    when (it.data != null && it.data.isNotEmpty()) {
                        true -> {
                            (binding.recyclerAlbumsList.adapter as AlbumsListAdapter).submitList(it.data)
                        }
                        else -> {
                            binding.textError.visibility = View.VISIBLE
                            binding.retryButton.visibility = View.VISIBLE
                            binding.textError.text = "No data available To Display"
                        }
                    }
                }
                is DataResult.Failure -> {
                    binding.progress.visibility = View.GONE
                    binding.textError.visibility = View.VISIBLE
                    binding.retryButton.visibility = View.VISIBLE
                    binding.textError.text = it.exp.message
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.fetchAlbums()
            }
        }
    }


}