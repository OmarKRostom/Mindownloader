package com.omarkrostom.minddownloadlibrary.fileResources

interface BaseResource<R> {
    fun convert(dataStream: ByteArray): R
}