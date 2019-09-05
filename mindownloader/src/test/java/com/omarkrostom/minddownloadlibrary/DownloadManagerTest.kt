package com.omarkrostom.minddownloadlibrary

import android.graphics.Bitmap
import com.omarkrostom.minddownloadlibrary.di.DaggerMindownloaderComponent
import com.omarkrostom.minddownloadlibrary.fileResources.ImageResource
import com.omarkrostom.minddownloadlibrary.managers.DownloadManager
import junit.framework.Assert.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class DownloadManagerTest {

    @Inject
    lateinit var downloadManager: DownloadManager

    @Before
    fun setUp() {
        DaggerMindownloaderComponent
            .builder()
            .build()
            .inject(this)
    }

    @Test
    fun testFileDownloadedAndConverted() {
        val imageUrl = " https://images.unsplash.com/photo-1464550883968-cec281c19761?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=1080&fit=max&s=1881cd689e10e5dca28839e68678f432"
        val imageResource = ImageResource()
        val syncObject = Object() // Used as a blocker to pause and resume async task

        downloadManager.loadResource(
            imageUrl,
            imageResource,
            {
                assertTrue(downloadManager.isCached(imageUrl))
                synchronized(syncObject) {
                    syncObject.notifyAll()
                }
            },{
                assertNotNull(it)
                synchronized(syncObject) {
                    syncObject.notifyAll()
                }
            }
        )

        synchronized(syncObject) {
            syncObject.wait()
        }
    }

}