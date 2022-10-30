package com.jommaa.domain.repositories

import com.jommaa.domain.entities.Album

interface INetworkAlbumsRepository {

    suspend fun getAlbumsListFromServer(): List<Album>

}