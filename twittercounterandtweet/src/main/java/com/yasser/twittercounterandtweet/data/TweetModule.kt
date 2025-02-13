package com.yasser.twittercounterandtweet.data

import com.yasser.twittercounterandtweet.data.repository.TwitterApi
import com.yasser.twittercounterandtweet.data.repository.TwitterApiRepositoryImpl
import com.yasser.twittercounterandtweet.domain.repository.TwitterApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TweetModule {

    @Provides
    @Singleton
    fun provideTwitterApi(okHttpClient: OkHttpClient): TwitterApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitter.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

      return retrofit.create(TwitterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTwitterApiRepository(twitterApi: TwitterApi): TwitterApiRepository {
        return TwitterApiRepositoryImpl(twitterApi)
    }

}