package com.yasser.twittercounterandtweet.data.repository

import com.yasser.twittercounterandtweet.data.dto.TweetResponseDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TwitterApi {
    @Headers("Content-Type: application/json")
    @POST("2/tweets")
    fun postTweet(@Body tweet: String): Call<TweetResponseDTO>
}