package com.yasser.simpletwittercharactercount

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object HomeScreen : Route()
    @Serializable
    data object TweetScreen : Route()
}