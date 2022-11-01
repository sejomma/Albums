package com.jommaa.albums.view.adapters

import com.jommaa.domain.entities.Album

class OnClickListener(val clickListener: (album: Album) -> Unit) {
    fun onClick(album: Album) = clickListener(album)
}