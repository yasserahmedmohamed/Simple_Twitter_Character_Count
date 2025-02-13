import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.URLEncoder
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.text.Charsets.UTF_8

class OAuthInterceptor(
    private val consumerKey: String,
    private val consumerSecret: String,
    private val accessToken: String,
    private val accessTokenSecret: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val signedRequest = originalRequest.signWithOAuth()
        return chain.proceed(signedRequest)
    }

    private fun Request.signWithOAuth(): Request {
        val nonce = UUID.randomUUID().toString().replace("-", "")
        val timestamp = (System.currentTimeMillis() / 1000).toString()
        val signatureMethod = "HMAC-SHA1"
        val version = "1.0"

        val params = mutableMapOf(
            "oauth_consumer_key" to consumerKey,
            "oauth_nonce" to nonce,
            "oauth_signature_method" to signatureMethod,
            "oauth_timestamp" to timestamp,
            "oauth_token" to accessToken,
            "oauth_version" to version
        )

        // Add query parameters to the signature base string
        val url = this.url
        for (i in 0 until url.querySize) {
            params[url.queryParameterName(i)] = url.queryParameterValue(i) ?:""
        }

        // Create the signature base string
        val baseString = "${this.method}&${url.encodedPath}&${params.toSortedMap()
            .map { "${it.key}=${URLEncoder.encode(it.value, UTF_8.name())}" }
            .joinToString("&")}"

        // Generate the signing key
        val signingKey = "${URLEncoder.encode(consumerSecret, UTF_8.name())}&${URLEncoder.encode(accessTokenSecret, UTF_8.name())}"

        // Generate the OAuth signature
        val signature = generateSignature(baseString, signingKey)

        // Add the OAuth header to the request
        val oauthHeader = "OAuth " + mapOf(
            "oauth_consumer_key" to consumerKey,
            "oauth_nonce" to nonce,
            "oauth_signature" to URLEncoder.encode(signature, UTF_8.name()),
            "oauth_signature_method" to signatureMethod,
            "oauth_timestamp" to timestamp,
            "oauth_token" to accessToken,
            "oauth_version" to version
        ).toSortedMap()
            .map { "${it.key}=\"${it.value}\"" }
            .joinToString(", ")

        return this.newBuilder()
            .header("Authorization", oauthHeader)
            .build()
    }

    private fun generateSignature(baseString: String, signingKey: String): String {
        val mac = Mac.getInstance("HmacSHA1")
        mac.init(SecretKeySpec(signingKey.toByteArray(UTF_8), "HmacSHA1"))
        val signatureBytes = mac.doFinal(baseString.toByteArray(UTF_8))
        return Base64.getEncoder().encodeToString(signatureBytes)
    }
}