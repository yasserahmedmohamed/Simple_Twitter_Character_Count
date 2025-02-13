import com.yasser.twittercounterandtweet.data.repository.OAuthHelper
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class OAuthInterceptor(
   private val consumerKey: String,
   private val   consumerSecret: String,
   private val  accessToken: String,
   private val  accessSecret: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val authHeader = OAuthHelper.generateOAuthHeader(
            consumerKey = consumerKey,
            consumerSecret = consumerSecret,
            accessToken = accessToken,
            accessSecret = accessSecret,
            method = originalRequest.method,
            url = originalRequest.url.toString()
        )

        val signedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", authHeader)
            .build()

        return chain.proceed(signedRequest)
    }
}