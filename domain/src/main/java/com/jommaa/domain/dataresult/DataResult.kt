package com.jommaa.domain.dataresult

import com.jommaa.domain.entities.Album

sealed class DataResult {
    class Success(val albums: List<Album>) : DataResult()
    object Loading : DataResult()
    class Failure(val exception: Exception,val message:String) : DataResult()
}