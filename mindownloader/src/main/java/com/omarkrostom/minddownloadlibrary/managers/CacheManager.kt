package com.omarkrostom.minddownloadlibrary.managers

import android.util.LruCache
import com.omarkrostom.minddownloadlibrary.models.Cache

class CacheManager(private val cacheConfig: Cache.Configuration) {
    /**
     * Properties
     */
    private val lruCache: LruCache<String, ByteArray> = LruCache(cacheConfig.cacheLimit)

    /**
     * Public Methods
     */
    fun checkFileExists(resourceUrl: String): Boolean = lruCache.get(resourceUrl) != null

    fun retrieveFile(resourceUrl: String): ByteArray = lruCache.get(resourceUrl)

    fun saveFile(url: String, byteStream: ByteArray) {
        lruCache.put(url, byteStream)
    }

    fun getCacheSize(): Int = cacheConfig.cacheLimit
}