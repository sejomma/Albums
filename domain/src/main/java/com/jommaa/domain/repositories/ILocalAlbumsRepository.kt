package com.jommaa.domain.repositories

import com.jommaa.domain.entities.Album

interface ILocalAlbumsRepository {

    suspend fun getAlbumsListFromDB(): List<Album>

    suspend fun putAlbumsInDB(albums: List<Album>)
}