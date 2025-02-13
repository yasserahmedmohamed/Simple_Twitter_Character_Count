package com.yasser.twittercounterandtweet.presentation

import androidx.lifecycle.ViewModel
import com.yasser.twittercounterandtweet.domain.useCase.TwitterCharacterCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TweetScreenViewModel @Inject constructor(private val characterCountUseCase: TwitterCharacterCountUseCase) :
    ViewModel() {

    var state = MutableStateFlow(TweetState())
        private set

    fun handleEvent(tweetEvent: TweetEvent) {
        when (tweetEvent) {
            is TweetEvent.OnTweetChanged -> {
                val characterCount = getCharacterCount(tweetEvent.tweet)
                if (characterCount<=280){
                    state.update {
                        it.copy(
                            tweet = tweetEvent.tweet,
                            characterCount = characterCount
                        )
                    }
                }

            }

            is TweetEvent.OnTweetSubmit -> {
                // Submit the tweet
            }

            TweetEvent.OnClearClicked -> {
                state.update {
                    it.copy(
                        tweet = "",
                        characterCount = 0
                    )
                }
            }
        }
    }

    private fun getCharacterCount(tweet: String): Int {
        return characterCountUseCase(tweet)
    }
}