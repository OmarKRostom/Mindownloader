package com.omarkrostom.minddownloadlibrary.managers

import android.webkit.URLUtil
import com.omarkrostom.minddownloadlibrary.fileResources.BaseResource
import com.omarkrostom.minddownloadlibrary.models.NetworkError
import com.omarkrostom.minddownloadlibrary.models.NetworkError.Companion.INTERNAL_NETWORK_ERROR
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import javax.inject.Inject

class DownloadManager @Inject constructor(
    private val httpClient: OkHttpClient,
    private val cacheManager: CacheManager
) {

    /**
     * Public fields
     */
    companion object {
        const val MAL_FORMATTED_URL = "MAL_FORMATTED_URL"
    }

    /**
     * Private properties
     */
    private val networkCalls: HashMap<String, Call> = HashMap()

    /**
     * Public methods
     */
    fun <R> loadResource(
        url: String,
        resource: BaseResource<R>,
        onResourceReady: (R) -> Unit,
        onResourceError: (NetworkError) -> Unit
    ) {
        downloadOrRetrieve(url, resource, onResourceReady, onResourceError)
    }

    fun cancelLoading(url: String) {
        networkCalls[url]?.cancel()
    }

    fun isCached(url: String) = cacheManager.checkFileExists(url)
    
    /**
     * Private methods
     */
    private fun <R> downloadOrRetrieve(
        url: String,
        resource: BaseResource<R>,
        onResourceReady: (R) -> Unit,
        onResourceError: (NetworkError) -> Unit
    ) {
        if (cacheManager.checkFileExists(url)) {
            onResourceReady(resource.convert(cacheManager.retrieveFile(url)))
            return
        }

        val resourceRequest = Request.Builder().url(url).build()
        val networkCall = httpClient.newCall(resourceRequest)
        networkCalls[url] = networkCall
        val disposable = Observable.create(ObservableOnSubscribe<Response> {
            it.onNext(networkCall.execute())
        }).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        val byteStream = it.byteStream()
                        val byteArray = byteStream.readBytes()
                        cacheManager.saveFile(url, byteArray)
                        onResourceReady(resource.convert(byteArray))
                    }
                } else {
                    onResourceError(NetworkError(response.code(), response.message()))
                }
            }, {
                onResourceError(NetworkError(INTERNAL_NETWORK_ERROR, it.localizedMessage))
            })
    }

}