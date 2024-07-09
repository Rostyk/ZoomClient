package com.test.zoomclient.domain.model.repository

import com.test.zoomclient.data.network.retrofit.LoginBody
import com.test.zoomclient.data.network.retrofit.RetrofitClient
import com.test.zoomclient.data.network.retrofit.LoginAPI
import com.test.zoomclient.domain.model.User
import com.test.zoomclient.presentation.ui.screens.login.componenets.Credentials
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginRepository {

    private val client = RetrofitClient.getInstance()

    fun login(credentials: Credentials): User {

        val loginApi = client.create(LoginAPI::class.java)

        GlobalScope.launch {
            val body = LoginBody(credentials.login, credentials.pwd)
            val result = loginApi.login(body)
            return User(result.body()!!.access_token)
        }
    }
}