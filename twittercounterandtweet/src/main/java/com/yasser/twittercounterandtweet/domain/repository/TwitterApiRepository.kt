package com.yasser.twittercounterandtweet.domain.repository

import com.yasser.twittercounterandtweet.data.dto.TweetResponseDTO
import com.yasser.twittercounterandtweet.domain.model.PostedTweetData
import retrofit2.Call
import retrofit2.Response

interface TwitterApiRepository {
    suspend fun postTweet(tweet: String):  Response<TweetResponseDTO>?
}