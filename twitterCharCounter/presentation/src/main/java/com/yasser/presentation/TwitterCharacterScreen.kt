package com.yasser.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TwitterCharacterScreen() {

    Log.e("TwitterCharacterScreen", "TwitterAccessToken: ${BuildConfig.TwitterAccessToken}")
}

@Preview(showBackground = true)
@Composable
private fun TwitterCharacterScreenPreview() {

}