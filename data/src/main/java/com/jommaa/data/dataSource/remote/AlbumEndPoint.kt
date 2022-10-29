package com.jommaa.data.dataSource.remote

import com.jommaa.data.dto.AlbumDto
import retrofit2.http.GET

interface AlbumEndPoint {
    @GET("/img/shared/technical-test.json")
    suspend fun getAlbumsList(): List<AlbumDto>
}