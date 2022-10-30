package com.jommaa.albums.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jommaa.albums.databinding.MainFragmentBinding
import com.jommaa.albums.view.adapters.AlbumsListAdapter
import com.jommaa.albums.viewmodel.MainViewModel
import com.jommaa.domain.dataresult.DataResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val vm: MainViewModel by viewModels()
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
            adapter = AlbumsListAdapter(null)
        }
        binding.retryButton.setOnClickListener {
            vm.fetchAlbums()
        }
        vm.getAlbums().observe(viewLifecycleOwner, Observer<DataResult> {
            when (it) {
                is DataResult.Loading -> {
                    binding.textError.visibility = View.GONE
                    binding.retryButton.visibility = View.GONE
                    binding.progress.visibility = View.VISIBLE
                }
                is DataResult.Success -> {
                    binding.progress.visibility = View.GONE
                    when(it.albums != null && it.albums.isNotEmpty()){
                        true->{
                            (binding.recyclerAlbumsList.adapter as AlbumsListAdapter).submitList(it.albums)
                        }
                        else->{
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
                    binding.textError.text = it.message
                }
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.fetchAlbums()
            }
        }
}}