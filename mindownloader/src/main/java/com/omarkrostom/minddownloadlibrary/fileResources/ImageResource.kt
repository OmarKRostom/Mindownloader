package com.omarkrostom.minddownloadlibrary.fileResources

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.InputStream

class ImageResource: BaseResource<Bitmap> {
    override fun convert(dataStream: ByteArray): Bitmap = BitmapFactory
        .decodeStream(dataStream.inputStream())
}