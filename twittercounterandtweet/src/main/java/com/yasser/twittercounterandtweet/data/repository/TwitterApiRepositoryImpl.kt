package com.yasser.twittercounterandtweet.data.repository

import com.yasser.twittercounterandtweet.data.dto.TweetResponseDTO
import com.yasser.twittercounterandtweet.domain.repository.TwitterApiRepository
import retrofit2.Response
import javax.inject.Inject


class TwitterApiRepositoryImpl @Inject constructor(
    private val twitterApi: TwitterApi,
) : TwitterApiRepository {
    override suspend fun postTweet(tweet: String): Response<TweetResponseDTO>? {
        return twitterApi.postTweet(tweet).execute()
    }
}