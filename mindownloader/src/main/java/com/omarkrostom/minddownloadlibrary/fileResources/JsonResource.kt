package com.omarkrostom.minddownloadlibrary.fileResources

import org.json.JSONObject
import org.json.JSONTokener
import java.io.InputStream

class JsonResource: BaseResource<JSONObject> {
    override fun convert(dataStream: ByteArray): JSONObject
            = JSONObject(JSONTokener(dataStream.toString()))
}