package com.jommaa.domain.dataresult

sealed class DataResult<out R: Any> {
    object Loading:DataResult<Nothing>()
    data class Success<out T:Any>(val data: T):DataResult<T>()
    data class Failure(val exp: CustomException):DataResult<Nothing>()
}