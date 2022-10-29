package com.jommaa.data.dataSource.local.datsource

import com.jommaa.data.dataSource.local.AlbumDB
import com.jommaa.data.dataSource.local.entities.AlbumEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AlbumsDataSource @Inject constructor(@ApplicationContext val context: android.content.Context) {
    val dataBase = AlbumDB.invoke(context)

    fun insertAlbum(album: AlbumEntity) {
        return dataBase.AlbumsDao().insert(album)
    }

    fun getAllAlbums(): List<AlbumEntity> {
        return dataBase.AlbumsDao().getAll()
    }

    fun getAlbumById(id: Int): List<AlbumEntity> {
        return dataBase.AlbumsDao().loadById(id)
    }

    fun getAlbumsCount(): Int {
        return dataBase.AlbumsDao().getAlbumsCount()
    }


}