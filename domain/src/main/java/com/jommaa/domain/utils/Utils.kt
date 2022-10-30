package com.jommaa.domain.utils

class Utils {

    companion object{
         fun isNotEmpty(list: List<*>?): Boolean {
            return list != null && list.isNotEmpty()
        }
    }
}