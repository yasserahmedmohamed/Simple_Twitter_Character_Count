package com.yasser.simpletwittercharactercount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.yasser.simpletwittercharactercount.ui.theme.Blue

@Composable
fun HomeScreen(navigateToTweetScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
                contentColor = Color.White
            ),
            onClick = navigateToTweetScreen
        ) {
            Text("Open Twitter Counter")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(){}
}

