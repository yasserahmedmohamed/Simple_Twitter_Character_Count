package com.yasser.twittercounterandtweet.data.dto


import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("detail")
    val detail: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
)