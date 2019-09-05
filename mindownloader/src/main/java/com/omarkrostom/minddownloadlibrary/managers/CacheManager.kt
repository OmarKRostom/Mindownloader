package com.omarkrostom.minddownloadlibrary.managers

import android.util.LruCache
import com.omarkrostom.minddownloadlibrary.models.Cache

class CacheManager(private val cacheConfig: Cache.Configuration) {
    /**
     * Properties
     */
    val lruCache: LruCache<String, ByteArray>
            = LruCache(cacheConfig.cacheLimit * 1024 * 1024)

    /**
     * Public Methods
     */
    fun checkFileExists(resourceUrl: String): Boolean = lruCache.get(resourceUrl) != null

    fun retrieveFile(resourceUrl: String): ByteArray = lruCache.get(resourceUrl)

    fun saveFile(url: String, byteStream: ByteArray) {
        lruCache.put(url, byteStream)
    }

    fun deleteFile(resourceUrl: String) {
        lruCache.remove(resourceUrl)
    }

    fun getCacheSize(): Int = cacheConfig.cacheLimit
}