package com.test.zoomclient.data.network.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class FirebaseData(val token: String, val uid: String)
data class LoginResponse(val access_token: String, val firbase: FirebaseData)
data class LoginBody(val email: String, val password: String)

interface LoginAPI {
    @POST("/api/v1/auth")
    suspend fun login(@Body body: LoginBody) : Response<LoginResponse>
}