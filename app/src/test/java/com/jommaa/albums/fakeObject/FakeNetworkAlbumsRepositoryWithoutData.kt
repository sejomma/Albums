package com.jommaa.domain.fakeObject

import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.INetworkAlbumsRepository
import java.util.*

class FakeNetworkAlbumsRepositoryWithoutData: INetworkAlbumsRepository {

    override suspend fun getAlbumsListFromServer(): List<Album> {
        return listOf()
    }
}