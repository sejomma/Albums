package com.jommaa.domain.fakeObject

import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.INetworkAlbumsRepository
import java.util.*

class FakeNetworkAlbumsRepositoryWithData: INetworkAlbumsRepository {

    val album1=  Album(
        albumId = 1,
        id = 1,
        title = "Album1",
        url = " https://via.placeholder.com/600/92c952",
        thumbnailUrl = "https//via.placeholder.com/150/92c952"
    )

    val album2=  Album(
        albumId = 2,
        id = 2,
        title = "Album2",
        url = " https://via.placeholder.com/600/92c952",
        thumbnailUrl = "https//via.placeholder.com/150/92c952"
    )
    val albumsList = Arrays.asList(album1,album2)

    override suspend fun getAlbumsListFromServer(): List<Album> {
        return albumsList
    }
}