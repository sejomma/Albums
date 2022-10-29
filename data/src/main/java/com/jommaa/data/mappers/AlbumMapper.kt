package com.jommaa.data.mappers

import com.jommaa.data.dataSource.local.entities.AlbumEntity
import com.jommaa.data.dto.AlbumDto
import com.jommaa.domain.entities.Album
import javax.inject.Inject

class AlbumMapper @Inject constructor() {

    fun albumDTOtoAlbum(albumDto: AlbumDto): Album {
        return Album(
            albumId = albumDto.albumId,
            id = albumDto.id,
            title = albumDto.title,
            url = albumDto.url,
            thumbnailUrl = albumDto.thumbnailUrl
        )
    }

    fun albumEntitytoAlbum(albumEntity: AlbumEntity): Album {
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

    fun albumToAlbumDTO(album: Album): AlbumDto {
        return AlbumDto(
            albumId = album.albumId,
            id = album.id,
            title = album.title,
            url = album.url,
            thumbnailUrl = album.thumbnailUrl
        )
    }
}