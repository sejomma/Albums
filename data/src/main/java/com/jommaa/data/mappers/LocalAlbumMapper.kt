package com.jommaa.data.mappers

import com.jommaa.data.dataSource.local.entities.AlbumEntity
import com.jommaa.domain.entities.Album
import javax.inject.Inject

class LocalAlbumMapper @Inject constructor() {

    fun albumEntityToAlbum(albumEntity: AlbumEntity): Album {
        return Album(
            albumId = albumEntity.albumId,
            id = albumEntity.id,
            title = albumEntity.title,
            url = albumEntity.url,
            thumbnailUrl = albumEntity.thumbnailUrl
        )
    }

    fun albumToAlbumEntity(album: Album): AlbumEntity {
        return AlbumEntity(
            albumId = album.albumId,
            id = album.id,
            title = album.title,
            url = album.url,
            thumbnailUrl = album.thumbnailUrl
        )
    }
}