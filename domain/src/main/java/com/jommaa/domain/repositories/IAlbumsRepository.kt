package com.jommaa.domain.repositories

import com.jommaa.domain.entities.Album

interface IAlbumsRepository {

    suspend fun getAlbumsListFromServer(): List<Album>

    suspend fun getAlbumsListFromDB(): List<Album>

    suspend fun putAlbumsInDB(albums: List<Album>)
}