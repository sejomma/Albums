package com.jommaa.albums.fakeObject

import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.ILocalAlbumsRepository
import java.util.*

class FakeLocalAlbumsRepositoryWithData : ILocalAlbumsRepository {
    val album1=  Album(
        albumId = 1,
        id = 1,
        title = "Album1",
        url = " https://via.placeholder.com/600/92c952",
        thumbnailUrl = "https//via.placeholder.com/150/92c952"
    )
    val albumsList = Arrays.asList(album1)
    override suspend fun getAlbumsListFromDB(): List<Album> {
        return albumsList
    }

    override suspend fun putAlbumsInDB(albums: List<Album>) {
        TODO("Not yet implemented")
    }
}