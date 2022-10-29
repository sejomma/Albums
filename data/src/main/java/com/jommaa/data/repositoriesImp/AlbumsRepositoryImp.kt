package com.jommaa.data.repositoriesImp

import com.jommaa.data.dataSource.local.datsource.AlbumsDataSource
import com.jommaa.data.dataSource.remote.AlbumsApi
import com.jommaa.data.mappers.AlbumMapper
import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.IAlbumsRepository
import javax.inject.Inject


class AlbumsRepositoryImp @Inject constructor(
    private val api: AlbumsApi,
    private val dataSource: AlbumsDataSource,
    private val mapper: AlbumMapper
) : IAlbumsRepository {

    override suspend fun getAlbumsListFromServer(): List<Album> {
        return api.getAlbumsList().map { mapper.albumDTOtoAlbum(it) }
    }

    override suspend fun getAlbumsListFromDB(): List<Album> {
        return dataSource.getAllAlbums().map { mapper.albumEntitytoAlbum(it) }
    }

    override suspend fun putAlbumsInDB(albums: List<Album>) {
        for (album in albums) {
            dataSource.insertAlbum(mapper.albumToAlbumEntity(album))
        }
    }
}