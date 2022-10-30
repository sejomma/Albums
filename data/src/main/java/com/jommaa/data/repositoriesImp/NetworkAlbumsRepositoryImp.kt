package com.jommaa.data.repositoriesImp

import com.jommaa.data.dataSource.remote.AlbumsApi
import com.jommaa.data.mappers.NetworkAlbumMapper
import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.INetworkAlbumsRepository
import javax.inject.Inject


class NetworkAlbumsRepositoryImp @Inject constructor(
    private val api: AlbumsApi,
    private val mapper: NetworkAlbumMapper
) : INetworkAlbumsRepository {

    override suspend fun getAlbumsListFromServer(): List<Album> {
        return api.getAlbumsList().map { mapper.albumDTOtoAlbum(it) }
    }
}