package com.yasser.twittercounterandtweet.presentation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yasser.twittercounterandtweet.R
import com.yasser.twittercounterandtweet.presentation.components.CharCountComponent
import com.yasser.twittercounterandtweet.presentation.ui.TweetTextStyle

@Composable
fun TweetScreen(
    tweetState: TweetState,
    onTweetEvent: (TweetEvent) -> Unit,
    onBackClicked: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        style = TweetTextStyle,
                        text = stringResource(R.string.title)
                    )
                    Row(
                        modifier = Modifier.padding(vertical = 10.dp),

                        ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Info"
                        )
                    }
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = Color(0xFFF6F6F6),
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Icon(
                modifier = Modifier.padding(top = 20.dp),
                painter = painterResource(R.drawable.ic_twitter),
                tint = Color(0xFF03A9F4),
                contentDescription = "Tweet"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CharCountComponent(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.char_type),
                    description = "${tweetState.characterCount}/${stringResource(R.string.char_count)}"
                )
                CharCountComponent(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.char_remaining),
                    description = "${stringResource(R.string.char_count).toInt() - tweetState.characterCount}"
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,

                    ),
                border = BorderStroke(1.dp, Color(0xFFEDEDED))
            ) {
                TextField(
                    value = tweetState.tweet,
                    onValueChange = {
                        onTweetEvent(TweetEvent.OnTweetChanged(it))
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.tweet_label),
                            style = TweetTextStyle,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF5E6160)
                        )
                    },

                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        ClipData.newPlainText("tweet", tweetState.tweet).also {
                            val clipboard =
                                context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                            clipboard.setPrimaryClip(it)
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00A970),
                    )
                ) {
                    Text(
                        text = stringResource(R.string.copy_text),
                        style = TweetTextStyle,
                        color = Color.White
                    )
                }

                Button(
                    onClick = {
                        onTweetEvent(TweetEvent.OnClearClicked)
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFDC0125),
                    )
                ) {
                    Text(
                        text = stringResource(R.string.clear_text),
                        style = TweetTextStyle,
                        color = Color.White
                    )
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                onClick = {
                    onTweetEvent(TweetEvent.OnTweetSubmit)
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF03A9F4),
                )
            ) {
                Text(
                    text = stringResource(R.string.post_tweet),
                    style = TweetTextStyle,
                    color = Color.White
                )
            }

        }
    }
}


@Preview
@Composable
private fun TweetScreenPreview() {
    TweetScreen(
        tweetState = TweetState(
            tweet = "",
            characterCount = 11
        ),
        onTweetEvent = {},
        onBackClicked = {}
    )
}