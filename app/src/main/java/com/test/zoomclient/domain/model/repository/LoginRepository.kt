package com.test.zoomclient.domain.model.repository

import android.util.Log
import com.test.zoomclient.data.network.retrofit.LoginBody
import com.test.zoomclient.data.network.retrofit.RetrofitClient
import com.test.zoomclient.data.network.retrofit.LoginAPI
import com.test.zoomclient.domain.model.User
import com.test.zoomclient.presentation.ui.screens.login.Credentials
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginRepository {

    private val client = RetrofitClient.getInstance()

    suspend fun login(credentials: Credentials): User {

        val loginApi = client.create(LoginAPI::class.java)
        val body = LoginBody(credentials.login, credentials.pwd)
        val result = loginApi.login(body)
        RetrofitClient.authToken = result.body()!!.access_token
        return User(result.body()!!.access_token)
    }
}