package com.yasser.simpletwittercharactercount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.yasser.simpletwittercharactercount.ui.theme.Blue
import com.yasser.simpletwittercharactercount.ui.theme.SimpleTwitterCharacterCountTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          val  navController = rememberNavController()
            SimpleTwitterCharacterCountTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppGraph(
                        navController = navController,
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}