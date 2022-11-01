package com.jommaa.albums.di

import com.jommaa.domain.repositories.ILocalAlbumsRepository
import com.jommaa.domain.repositories.INetworkAlbumsRepository
import com.jommaa.domain.usecases.GetAlbumsFromLocalUseCase
import com.jommaa.domain.usecases.GetAlbumsFromNetworkUseCase
import com.jommaa.domain.usecases.PutAlbumsInLocalDBUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideGetAlbumsFromNetworkUseCase(repo: INetworkAlbumsRepository): GetAlbumsFromNetworkUseCase {
        return GetAlbumsFromNetworkUseCase(repo)
    }

    @Provides
    fun provideGetAlbumsFromLocalUseCase(repo: ILocalAlbumsRepository): GetAlbumsFromLocalUseCase {
        return GetAlbumsFromLocalUseCase(repo)
    }

    @Provides
    fun providePutAlbumsInLocalDBUseCase(repo: ILocalAlbumsRepository): PutAlbumsInLocalDBUseCase {
        return PutAlbumsInLocalDBUseCase(repo)
    }
}