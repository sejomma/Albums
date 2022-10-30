package com.jommaa.data.mapper

import com.jommaa.data.mappers.LocalAlbumMapper
import com.jommaa.data.mockObjects.MockObjects
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalAlbumMapperUnitTest {
    lateinit var mapper: LocalAlbumMapper

    @Before
    fun setup() {
        mapper = LocalAlbumMapper()
    }

    @Test
    fun `AlbumEntityToAlbum mapper should return the right values`() {
        val albumEntity = MockObjects.fakeAlbumEntityObject()
        val album = mapper.albumEntityToAlbum(albumEntity)
        Assert.assertEquals(albumEntity.id, album.id)
    }

    @Test
    fun `AlbumToAlbumEntity mapper should return the right values`() {
        val album = MockObjects.fakeAlbumObject()
        val albumDto = mapper.albumToAlbumEntity(album)
        Assert.assertEquals(albumDto.id, album.id)
    }
}