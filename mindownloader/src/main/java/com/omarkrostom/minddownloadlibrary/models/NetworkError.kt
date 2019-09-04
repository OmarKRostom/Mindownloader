package com.omarkrostom.minddownloadlibrary.models

data class NetworkError(
    val errorCode: Int,
    val errorValue: String
) {
    companion object {
        const val INTERNAL_NETWORK_ERROR = 999
    }
}