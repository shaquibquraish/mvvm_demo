package com.shaquibquraishi.kotlindemo.ui.auth

import androidx.lifecycle.LiveData
import com.apollographql.apollo.api.Response
import com.example.LoginMutation

interface AuthListener {
    fun onStarted()
    fun onSuccess(loginResponse: LoginMutation.Login?)
    fun onFailure(message: String)
}