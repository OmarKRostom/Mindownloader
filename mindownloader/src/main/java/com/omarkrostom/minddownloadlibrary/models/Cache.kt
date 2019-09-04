package com.omarkrostom.minddownloadlibrary.models

import com.omarkrostom.minddownloadlibrary.models.Cache.Configuration.ConfigurationConstants.DEFAULT_CACHE_SIZE

/**
 * This is the Cache configuration needed in DownloadManager
 */
class Cache(
    private val configuration: Configuration
) {
    class Configuration(val cacheLimit: Int = DEFAULT_CACHE_SIZE) {
        object ConfigurationConstants {
            const val DEFAULT_CACHE_SIZE = 256
        }
    }
}