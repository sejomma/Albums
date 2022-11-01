package com.jommaa.domain.usecases

import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.ILocalAlbumsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PutAlbumsInLocalDBUseCase(private val repo: ILocalAlbumsRepository) {

    suspend fun invoke(albums: List<Album>) {
        return withContext(Dispatchers.IO) { insertAlbumsInDB(albums) }
    }

    /**
     * Insert into local DB an albums list
     * @param:albumsList : albums list that will be saved
     **/
    private suspend fun insertAlbumsInDB(albumsList: List<Album>) {
        GlobalScope.launch(Dispatchers.IO) {
            repo.putAlbumsInDB(albumsList)
        }
    }
}