package com.omarkrostom.mindownloader.models

import com.google.gson.annotations.SerializedName

data class PostItem(
    @SerializedName("id") val id: String,
    @SerializedName("likes") val likes: Int,
    @SerializedName("urls") val postItemUrls: PostItemUrls,
    @SerializedName("user") val user: User
)

data class PostItemUrls(
    @SerializedName("raw") val raw: String,
    @SerializedName("regular") val regular: String
)