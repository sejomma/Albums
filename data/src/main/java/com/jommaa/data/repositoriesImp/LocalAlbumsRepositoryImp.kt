package com.jommaa.data.repositoriesImp

import com.jommaa.data.dataSource.local.datsource.AlbumsDataSource
import com.jommaa.data.mappers.LocalAlbumMapper
import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.ILocalAlbumsRepository
import javax.inject.Inject

class LocalAlbumsRepositoryImp @Inject constructor(
    private val dataSource: AlbumsDataSource,
    private val mapper: LocalAlbumMapper
) : ILocalAlbumsRepository {

    override suspend fun getAlbumsListFromDB(): List<Album> {
        return dataSource.getAllAlbums().map { mapper.albumEntityToAlbum(it) }
    }

    override suspend fun putAlbumsInDB(albums: List<Album>) {
        for (album in albums) {
            dataSource.insertAlbum(mapper.albumToAlbumEntity(album))
        }
    }
}