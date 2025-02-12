package com.yasser.simpletwittercharactercount

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yasser.twittercounterandtweet.presentation.TweetScreen

@Composable
fun AppGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        composable<Route.HomeScreen>{
            HomeScreen(){
                navController.navigate(Route.TweetScreen)
            }
        }

        composable<Route.TweetScreen>{
            TweetScreen()
        }



    }

}