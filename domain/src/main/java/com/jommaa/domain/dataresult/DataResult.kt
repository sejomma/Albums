package com.jommaa.domain.dataresult

import com.jommaa.domain.entities.Album

sealed class DataResult<out R: Any> {
    object Loading:DataResult<Nothing>()
    data class Success<out T:Any>(val data: T):DataResult<T>()
    data class Failure(val exp: CustomException):DataResult<Nothing>()
}