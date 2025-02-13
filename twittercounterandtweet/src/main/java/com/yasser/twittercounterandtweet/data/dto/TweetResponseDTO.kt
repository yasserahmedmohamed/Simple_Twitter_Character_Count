package com.yasser.twittercounterandtweet.data.dto


import com.google.gson.annotations.SerializedName

data class TweetResponseDTO(
    @SerializedName("data")
    val success: Data?,
    @SerializedName("errors")
    val errors: List<Error?>?
)