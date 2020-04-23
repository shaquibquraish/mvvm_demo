package com.shaquibquraishi.kotlindemo.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.api.Response
import com.example.LoginMutation
import com.shaquibquraishi.kotlindemo.data.repository.UserRepository
import java.util.logging.Handler
import java.util.logging.LogRecord

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid Email Or Password!")
            return
        }

        val loginResponse: Response<LoginMutation.Data>? =
                    userRepository.userLogin(email!!, password!!)



        Log.e("login mutation authView",loginResponse.toString())

            if (loginResponse == null) {
                authListener?.onFailure("Server Error!")
            } else if (loginResponse.data()?.login == null) {
                authListener?.onFailure("Invalid Email or Password!")

            } else {
                val user: LoginMutation.Login? = loginResponse.data()!!.login
                userRepository.saveUserDetails(user?.token, user?.id, user?.email, user?.name)
                authListener?.onSuccess(loginResponse.data()!!.login)
                Log.e("saved", "yes")
            }



    }


}