package com.jommaa.domain.usecases


import com.jommaa.domain.dataresult.CustomException
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.INetworkAlbumsRepository
import com.jommaa.domain.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException


class GetAlbumsFromNetworkUseCase(private val albumRepository: INetworkAlbumsRepository) {

    /**
     * Call Album Api to retrieve the albums list.
     * If Album Api returns a not empty list, all albums returned will be saved in local data Base
     * If Album Api return an exception, this method call the local db for getting the albums list that is saved in the last call
     * If Album Api return an exception and no data in local data Base, this method return an Exception with "No available data" message
     */
    suspend fun execute(): DataResult<List<Album>> {
        return  withContext(Dispatchers.IO) {getAlbums()}

    }
    private suspend fun getAlbums(): DataResult<List<Album>> {
            try {
                val albumsList = albumRepository.getAlbumsListFromServer()
                return when (Utils.isNotEmpty(albumsList)) {
                    true -> {
                        DataResult.Success(albumsList)
                    }
                    else ->{
                        DataResult.Failure(CustomException(null,"No Data available from API"))
                    }
                }

            } catch (ex: Exception) {
                return DataResult.Failure(CustomException(null,"Error provides from API"))
            }
    }


}