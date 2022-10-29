package com.jommaa.leboncoin.di


import com.jommaa.data.dataSource.remote.AlbumEndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object EndPointModule {

    @Provides
    fun provideAlbumEndPoint(retrofit: Retrofit): AlbumEndPoint {
        return retrofit.create(AlbumEndPoint::class.java)
    }
}