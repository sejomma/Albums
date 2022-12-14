package com.jommaa.albums.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jommaa.albums.databinding.AlbumBinding
import com.jommaa.domain.entities.Album
import com.squareup.picasso.Picasso

class AlbumsListAdapter(
    private var data: MutableList<Album>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<AlbumsListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AlbumBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val binding = AlbumBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            data.let {
                with(it[position]) {
                    holder.itemView.setOnClickListener { onClickListener.onClick(data[position]) }
                    binding.albumTitle.text = this.title
                    Picasso.get().load(this.url).into(binding.albumIcon)
                }
            }
        }

    }


    override fun getItemCount(): Int {
        data.let {
            return it.size
        }
        return 0

    }

    fun submitList(newData: List<Album>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

}