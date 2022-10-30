package com.jommaa.data.mapper

import com.jommaa.data.mappers.NetworkAlbumMapper
import com.jommaa.data.mockObjects.MockObjects
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NetworkAlbumMapperUnitTest {
    lateinit var mapper: NetworkAlbumMapper

    @Before
    fun setup() {
        mapper = NetworkAlbumMapper()
    }

    @Test
    fun `AlbumDToToAlbum mapper should return the right values`() {
        val albumDto = MockObjects.fakeAlbumDtoObject()
        val album = mapper.albumDTOtoAlbum(albumDto)
        Assert.assertEquals(albumDto.id, album.id)
    }

    @Test
    fun `AlbumToAlbumDTO mapper should return the right values`() {
        val album = MockObjects.fakeAlbumObject()
        val albumDto = mapper.albumToAlbumDTO(album)
        Assert.assertEquals(albumDto.id, album.id)
    }


}