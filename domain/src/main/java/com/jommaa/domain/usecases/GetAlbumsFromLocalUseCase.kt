package com.jommaa.domain.usecases

import com.jommaa.domain.dataresult.CustomException
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.ILocalAlbumsRepository
import com.jommaa.domain.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAlbumsFromLocalUseCase(private val albumRepository: ILocalAlbumsRepository) {

    suspend fun execute(): DataResult<List<Album>> {
        return  withContext(Dispatchers.IO) {getAlbumsListFromDB()}

    }

    /**
     * get all albums from local DB
     */
    private suspend fun getAlbumsListFromDB(): DataResult<List<Album>> {
        try {
            val albums = albumRepository.getAlbumsListFromDB()
            return when (Utils.isNotEmpty(albums)) {
                true -> DataResult.Success(albums)
                else -> {
                    DataResult.Failure(
                        CustomException(
                            null,
                            "No data available to display, please make sure you have internet connection and try again"
                        )
                    )
                }
            }
        }
        catch (exception: Exception){
            return DataResult.Failure(CustomException(
                null,
                "Unknown Error, please try again"
            ))
        }
    }

    /**
     * Insert into local DB an albums list
     * @param:albumsList : albums list that will be saved
     **/

}


