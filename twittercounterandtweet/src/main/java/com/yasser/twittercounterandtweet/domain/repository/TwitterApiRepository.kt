package com.yasser.twittercounterandtweet.domain.repository

import com.yasser.twittercounterandtweet.data.dto.TweetResponseDTO
import com.yasser.twittercounterandtweet.domain.model.PostedTweetData
import retrofit2.Call

interface TwitterApiRepository {
    suspend fun postTweet(tweet: String): TweetResponseDTO?
}