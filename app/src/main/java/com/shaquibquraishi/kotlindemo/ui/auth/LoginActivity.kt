package com.shaquibquraishi.kotlindemo.ui.auth

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.LoginMutation
import com.shaquibquraishi.kotlindemo.R
import com.shaquibquraishi.kotlindemo.databinding.ActivityLoginBinding
import com.shaquibquraishi.kotlindemo.ui.home.HomeActivity
import com.shaquibquraishi.kotlindemo.util.hide
import com.shaquibquraishi.kotlindemo.util.show
import com.shaquibquraishi.kotlindemo.util.snackbar
import com.shaquibquraishi.kotlindemo.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.authListener = this


        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (!user.name.equals("")) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })
    }


    override fun onStarted() {
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
