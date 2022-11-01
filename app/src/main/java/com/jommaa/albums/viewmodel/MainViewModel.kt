package com.jommaa.albums.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.entities.Album
import com.jommaa.domain.usecases.GetAlbumsFromLocalUseCase
import com.jommaa.domain.usecases.GetAlbumsFromNetworkUseCase
import com.jommaa.domain.usecases.PutAlbumsInLocalDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAlbumFromNetworkUseCase: GetAlbumsFromNetworkUseCase,
    private val getAlbumFromLocalUseCase: GetAlbumsFromLocalUseCase,
    private val putAlbumsInLocalDBUseCase: PutAlbumsInLocalDBUseCase
) : ViewModel() {

    lateinit var selectedAlbum: Album
    private val albums = MutableLiveData<DataResult<List<Album>>>()

    fun getAlbums(): MutableLiveData<DataResult<List<Album>>> {
        return albums
    }

    /**
     * Start getting data
     */
    fun fetchAlbums() {
        albums.postValue(DataResult.Loading)
        viewModelScope.launch {
            val fromNetworkCall = getAlbumFromNetworkUseCase.execute()
            when (fromNetworkCall is DataResult.Success) {
                true -> {
                    albums.postValue(fromNetworkCall)
                    putAlbumsInLocalDBUseCase.invoke(fromNetworkCall.data)
                }
                false -> {
                    albums.postValue(getAlbumFromLocalUseCase.execute())
                }
            }
        }
    }
}