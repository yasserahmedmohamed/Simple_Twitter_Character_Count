package com.yasser.simpletwittercharactercount

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yasser.twittercounterandtweet.presentation.TweetScreen
import com.yasser.twittercounterandtweet.presentation.TweetScreenViewModel

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
            val viewModel: TweetScreenViewModel = hiltViewModel()
            TweetScreen(
                tweetState = viewModel.state.collectAsStateWithLifecycle().value,
                onTweetEvent =  viewModel::handleEvent
            ){
                navController.popBackStack()
            }
        }



    }

}