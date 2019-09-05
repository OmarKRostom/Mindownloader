package com.omarkrostom.minddownloadlibrary

import com.omarkrostom.minddownloadlibrary.di.DaggerMindownloaderComponent
import com.omarkrostom.minddownloadlibrary.managers.CacheManager
import com.omarkrostom.minddownloadlibrary.models.Cache
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import javax.inject.Inject

class CacheManagerTest {

    @Inject
    lateinit var cacheManager: CacheManager

    @Before
    fun setUp() {
        DaggerMindownloaderComponent
            .builder()
            .build()
            .inject(this)
    }

    @Test
    fun testCacheSizeUpdated() {
        val cacheConfiguration = Cache.Configuration(512) // This means 512 MB
        val cacheManager = CacheManager(cacheConfiguration)

        assertEquals(512, cacheManager.getCacheSize())
    }

    @Test
    fun testCacheFileAdded() {
        val resourceUrl = "https://mindownloader.com/"

        cacheManager.saveFile(resourceUrl, ByteArray(64))
        assertEquals(
            ByteArray(64),
            cacheManager.retrieveFile(resourceUrl)
        )

        cacheManager.deleteFile(resourceUrl)
        assertEquals(0, cacheManager.lruCache.size())
    }

}