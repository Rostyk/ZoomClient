package com.test.zoomclient.data.network.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var authToken: String = ""
    class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()
            if (!request.url().encodedPath().endsWith("auth")) {
                request = request.newBuilder().header("Authorization", "Bearer $authToken").build()
            }
            return chain.proceed(request)
        }
    }

    private val baseUrl = "https://staging.immediate-interaction.com"
    private var httpClient: Retrofit? = null

    fun getInstance(): Retrofit {
        if (httpClient == null) {
            val client = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor())
                .build()

            httpClient = Retrofit.Builder().baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return httpClient!!
    }
}
   