package com.yasser.twittercounterandtweet.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasser.twittercounterandtweet.data.dto.TweetResponseDTO
import com.yasser.twittercounterandtweet.domain.useCase.PostTwitterUseCase
import com.yasser.twittercounterandtweet.domain.useCase.TwitterCharacterCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import javax.inject.Inject

@HiltViewModel
class TweetScreenViewModel @Inject constructor(
    private val characterCountUseCase: TwitterCharacterCountUseCase,
    private val postTwitterUseCase: PostTwitterUseCase
) :
    ViewModel() {

    var state = MutableStateFlow(TweetState())
        private set

    fun handleEvent(tweetEvent: TweetEvent) {
        when (tweetEvent) {
            is TweetEvent.OnTweetChanged -> {
                val characterCount = getCharacterCount(tweetEvent.tweet)
                if (characterCount <= 280) {
                    state.update {
                        it.copy(
                            tweet = tweetEvent.tweet,
                            characterCount = characterCount
                        )
                    }
                }

            }

            is TweetEvent.OnTweetSubmit -> {
                viewModelScope.launch(Dispatchers.IO) {
                    println(postTwitterUseCase(state.value.tweet))
                }
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