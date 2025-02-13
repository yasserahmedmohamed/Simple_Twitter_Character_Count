package com.yasser.twittercounterandtweet.domain.useCase

import com.yasser.twittercounterandtweet.domain.repository.TwitterApiRepository
import javax.inject.Inject

class PostTwitterUseCase @Inject constructor(private val repo: TwitterApiRepository) {
    suspend operator fun invoke(tweet: String) = repo.postTweet(tweet)
}