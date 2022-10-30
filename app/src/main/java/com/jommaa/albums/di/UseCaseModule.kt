package com.jommaa.albums.di

import com.jommaa.domain.repositories.IAlbumsRepository
import com.jommaa.domain.usecases.GetAlbumsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideGetAlbumsUseCase(repo:IAlbumsRepository):GetAlbumsUseCase{
        return GetAlbumsUseCase(repo)
    }
}