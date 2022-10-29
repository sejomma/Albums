package com.jommaa.leboncoin.di

import com.jommaa.data.repositoriesImp.AlbumsRepositoryImp
import com.jommaa.domain.repositories.IAlbumsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAlbumsRepository(albumsRepositoryImp: AlbumsRepositoryImp): IAlbumsRepository

}
