package com.shaquibquraishi.kotlindemo.ui.auth

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apollographql.apollo.api.Response
import com.example.LoginMutation
import com.shaquibquraishi.kotlindemo.R
import com.shaquibquraishi.kotlindemo.data.network.ApolloConnector
import com.shaquibquraishi.kotlindemo.data.repository.UserRepository
import com.shaquibquraishi.kotlindemo.data.sharedPref.SharedPreferencesMethod
import com.shaquibquraishi.kotlindemo.databinding.ActivityLoginBinding
import com.shaquibquraishi.kotlindemo.util.hide
import com.shaquibquraishi.kotlindemo.util.show
import com.shaquibquraishi.kotlindemo.util.snackbar
import com.shaquibquraishi.kotlindemo.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import net.simplifiedcoding.mvvmsampleapp.ui.auth.AuthViewModelFactory

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apolloConnector = ApolloConnector
        val sharedPref = SharedPreferencesMethod(this)
        val userRepository = UserRepository(apolloConnector,sharedPref)
        val factory = AuthViewModelFactory(userRepository)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.authListener = this

    }


    override fun onStarted() {
        //login_progress.visibility = View.VISIBLE
        login_progress.show()
        root_Layout.snackbar("Login Started")
        toast("Login Started")
    }

    override fun onSuccess(loginResponse: LoginMutation.Login?) {
            Log.e("response", loginResponse.toString())
            toast(loginResponse?.name.toString())
            login_progress.hide()
    }

    override fun onFailure(message: String) {
        login_progress.hide()
        toast(message)
    }
}
