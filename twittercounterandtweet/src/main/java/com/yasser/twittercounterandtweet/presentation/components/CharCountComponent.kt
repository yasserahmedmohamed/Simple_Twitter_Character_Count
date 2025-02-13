package com.yasser.twittercounterandtweet.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yasser.twittercounterandtweet.R
import com.yasser.twittercounterandtweet.presentation.ui.TweetTextStyle

@Composable
fun CharCountComponent(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,

            ),
        border = BorderStroke(1.dp, Color(0xFFE6F6FE))
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE6F6FE))
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    style = TweetTextStyle,
                    text = title
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                style = TweetTextStyle,
                fontSize = 30.sp,
                text = description
            )

        }
    }
}


@Preview
@Composable
private fun CharCountComponentPreview() {
    CharCountComponent(
        title = stringResource(id = R.string.char_type),
        description = "0/280"
    )
}