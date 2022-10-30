package com.jommaa.data.api

import com.jommaa.data.dataSource.remote.AlbumEndPoint
import com.jommaa.data.dataSource.remote.AlbumsApi
import com.jommaa.data.mockObjects.MockObjects
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.*


@RunWith(MockitoJUnitRunner::class)
class AlbumApiTest {

    lateinit var albumsApi: AlbumsApi
    lateinit var albumEndPoint: AlbumEndPoint

    @Before
    fun setUp()
        {
            albumEndPoint = Mockito.mock(AlbumEndPoint::class.java)
        }
    @Test
    fun `Assert the albums returned by the api is the same list returned by Api object`(){
        runBlocking {

            Mockito.`when`(albumEndPoint.getAlbumsList()).thenReturn(MockObjects.fakeAlbumsDtoList())
            albumsApi = AlbumsApi(albumEndPoint)
                val albumResp = albumsApi.getAlbumsList()
                assertEquals(albumResp.size, MockObjects.fakeAlbumsDtoList().size)
                assertEquals(albumResp[0].id, MockObjects.fakeAlbumsDtoList()[0].id)
                assertEquals(albumResp[0].title, MockObjects.fakeAlbumsDtoList()[0].title)
            }
        }
    }


