package com.yasser.simpletwittercharactercount.di

import OAuthInterceptor
import com.yasser.simpletwittercharactercount.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(
                OAuthInterceptor(
                    consumerKey = BuildConfig.TwitterAPIKey,
                    consumerSecret = BuildConfig.TwitterAPIKeySecret,
                    accessToken =BuildConfig.TwitterAccessToken ,
                    accessTokenSecret = BuildConfig.TwitterAccessTokenSecret
                )
            )
            .build()
    }
}