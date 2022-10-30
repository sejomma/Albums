package com.jommaa.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jommaa.data.dataSource.local.AlbumDB
import com.jommaa.data.dataSource.local.dao.AlbumsDao
import com.jommaa.data.dataSource.local.entities.AlbumEntity
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumsDBTest {

    private lateinit var albumsDao: AlbumsDao
    private lateinit var db: AlbumDB

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AlbumDB::class.java
        ).build()
        albumsDao = db.AlbumsDao()
    }

    @After
    fun closeDB() {
        AlbumDB.close()
    }

    @Test
    fun writeOneAlbumAndReadIt() {
        val albumEntity = AlbumEntity(1, 1, "test", "test", "test")
        albumsDao.insert(albumEntity)

        val savedAlbums = albumsDao.getAll()

        assertThat(savedAlbums[0].id, CoreMatchers.equalTo(1))
        assertThat(savedAlbums[0].albumId, CoreMatchers.equalTo(1))
        assertThat(savedAlbums[0].title, CoreMatchers.equalTo("test"))
        assertThat(savedAlbums[0].url, CoreMatchers.equalTo("test"))
        assertThat(savedAlbums[0].thumbnailUrl, CoreMatchers.equalTo("test"))
        assertThat(albumsDao.getAlbumsCount(), CoreMatchers.equalTo(1))

    }

    @Test
    fun assert_that_loadById_method_returns_the_write_element() {
        val firstAlbumEntity = AlbumEntity(1, 1, "test", "test", "test")
        val secondAlbumEntity = AlbumEntity(2, 2, "test2", "test2", "test2")

        albumsDao.insert(firstAlbumEntity)
        albumsDao.insert(secondAlbumEntity)

        val savedAlbums = albumsDao.loadById(2)
        assertThat(savedAlbums[0].id, CoreMatchers.equalTo(2))
        assertThat(savedAlbums[0].albumId, CoreMatchers.equalTo(2))
        assertThat(savedAlbums[0].title, CoreMatchers.equalTo("test2"))
        assertThat(savedAlbums[0].url, CoreMatchers.equalTo("test2"))
        assertThat(savedAlbums[0].thumbnailUrl, CoreMatchers.equalTo("test2"))
    }

    @Test
    fun if_db_is_empty_then_return_zero_element() {
        val albumsList = albumsDao.getAll()
        assertThat(albumsList.size, CoreMatchers.equalTo(0))
    }


}