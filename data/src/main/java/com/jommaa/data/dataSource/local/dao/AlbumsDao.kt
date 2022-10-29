package com.jommaa.data.dataSource.local.dao

import androidx.room.*
import com.jommaa.data.dataSource.local.entities.AlbumEntity

@Dao
interface AlbumsDao {
    @Query("SELECT * FROM albumEntity")
    fun getAll(): List<AlbumEntity>

    @Query("SELECT * FROM albumEntity WHERE id=:Id")
    fun loadById(Id: Int): List<AlbumEntity>

    @Query("SELECT count(*) from albumEntity")
    fun getAlbumsCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg album: AlbumEntity)

    @Delete
    fun delete(album: AlbumEntity)
}