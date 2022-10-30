package com.jommaa.albums.fakeObject

import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.ILocalAlbumsRepository
import java.util.*

class FakeLocalAlbumsRepositoryWithoutData : ILocalAlbumsRepository {

    override suspend fun getAlbumsListFromDB(): List<Album> {
        return listOf()
    }

    override suspend fun putAlbumsInDB(albums: List<Album>) {
        TODO("Not yet implemented")
    }
}