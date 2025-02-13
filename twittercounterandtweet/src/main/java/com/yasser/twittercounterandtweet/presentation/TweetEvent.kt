package com.yasser.twittercounterandtweet.presentation

sealed class TweetEvent {
    data class OnTweetChanged(val tweet: String) : TweetEvent()
    data object OnTweetSubmit : TweetEvent()
    data object OnClearClicked : TweetEvent()
}