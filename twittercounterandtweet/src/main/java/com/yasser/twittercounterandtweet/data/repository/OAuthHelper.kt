package com.yasser.twittercounterandtweet.data.repository

import java.net.URLEncoder
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import android.util.Base64
import java.security.SecureRandom

object OAuthHelper {

    fun generateOAuthHeader(
        consumerKey: String,
        consumerSecret: String,
        accessToken: String,
        accessSecret: String,
        url: String,
        method: String
    ): String {
        val nonce = generateNonce()
        val timestamp = (System.currentTimeMillis() / 1000).toString()

        val params = sortedMapOf(
            "oauth_consumer_key" to consumerKey,
            "oauth_nonce" to nonce,
            "oauth_signature_method" to "HMAC-SHA1",
            "oauth_timestamp" to timestamp,
            "oauth_token" to accessToken,
            "oauth_version" to "1.0"
        )

        val baseString = createBaseString(method, url, params)
        val signingKey = "$consumerSecret&$accessSecret"
        val signature = generateSignature(baseString, signingKey)

        params["oauth_signature"] = signature

        return "OAuth " + params.map { "${it.key}=\"${encode(it.value)}\"" }
            .joinToString(", ")
    }

    private fun createBaseString(method: String, url: String, params: SortedMap<String, String>): String {
        val encodedParams = params.map { "${encode(it.key)}=${encode(it.value)}" }
            .joinToString("&")
        return "$method&${encode(url)}&${encode(encodedParams)}"
    }

    private fun generateSignature(data: String, key: String): String {
        val mac = Mac.getInstance("HmacSHA1")
        val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA1")
        mac.init(secretKey)
        return Base64.encodeToString(mac.doFinal(data.toByteArray()), Base64.NO_WRAP)
    }

    private fun generateNonce(): String {
        val random = SecureRandom()
        val bytes = ByteArray(32)
        random.nextBytes(bytes)
        return Base64.encodeToString(bytes, Base64.NO_WRAP or Base64.URL_SAFE)
            .replace("[^a-zA-Z0-9]".toRegex(), "")
    }

    private fun encode(value: String): String = URLEncoder.encode(value, "UTF-8")
}
