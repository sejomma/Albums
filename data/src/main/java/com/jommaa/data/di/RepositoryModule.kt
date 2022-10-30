package com.jommaa.leboncoin.di

import com.jommaa.data.repositoriesImp.LocalAlbumsRepositoryImp
import com.jommaa.data.repositoriesImp.NetworkAlbumsRepositoryImp
import com.jommaa.domain.repositories.ILocalAlbumsRepository
import com.jommaa.domain.repositories.INetworkAlbumsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNetworkAlbumsRepository(albumsRepositoryImp: NetworkAlbumsRepositoryImp): INetworkAlbumsRepository

    @Binds
    abstract fun bindLocalAlbumsRepository(albumsRepositoryImp: LocalAlbumsRepositoryImp): ILocalAlbumsRepository

}
