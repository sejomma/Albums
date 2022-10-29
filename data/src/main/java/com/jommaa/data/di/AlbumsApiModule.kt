package com.jommaa.leboncoin.di

import com.jommaa.data.dataSource.remote.AlbumEndPoint
import com.jommaa.data.dataSource.remote.AlbumsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AlbumsApiModule {
    @Provides
    fun provideAlbumsApi(albumEndPoint: AlbumEndPoint): AlbumsApi {
        return AlbumsApi(albumEndPoint)
    }
}

