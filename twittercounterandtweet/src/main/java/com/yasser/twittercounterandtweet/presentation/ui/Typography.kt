package com.yasser.twittercounterandtweet.presentation.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.yasser.twittercounterandtweet.R

val DINFamily = FontFamily(
    Font(R.font.arab_din_next_regular, FontWeight.Normal),
    Font(R.font.arab_din_next_medium, FontWeight.Medium),
)
val TweetTextStyle = TextStyle(
    fontFamily = DINFamily,
    fontWeight = FontWeight(500),
    textAlign = TextAlign.Center,
    color = Color(0xFF1C211F),
)