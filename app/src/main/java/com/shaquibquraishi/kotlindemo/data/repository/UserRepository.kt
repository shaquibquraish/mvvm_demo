package com.shaquibquraishi.kotlindemo.data.repository

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.LoginMutation
import com.shaquibquraishi.kotlindemo.data.network.ApolloConnector
import com.shaquibquraishi.kotlindemo.data.sharedPref.SharedPreferencesMethod


class UserRepository(
    private val apolloConnector: ApolloConnector,
    private val sharedPref: SharedPreferencesMethod
) {

  fun userLogin(email: String, password: String): Response<LoginMutation.Data>? {
        var loginResponse: Response<LoginMutation.Data>? = null
            apolloConnector.setupApollo().mutate(LoginMutation(email, password)).enqueue(object :
                ApolloCall.Callback<LoginMutation.Data>() {
                override fun onFailure(e: ApolloException) {
                    Log.e("fail msg", e.message)
                         loginResponse = null


                }

                override fun onResponse(response: Response<LoginMutation.Data>) {
                    Log.e("msg2", response.data().toString())
                        loginResponse = response

                }


            })

            return loginResponse


        }

        fun saveUserDetails(token: String?, id: String?, email: String?, name: String?) {
            sharedPref.setString(sharedPref.TOKEN, token)
            sharedPref.setString(sharedPref.USER_ID, id)
            sharedPref.setString(sharedPref.USER_EMAIL, email)
            sharedPref.setString(sharedPref.USER_NAME, name)
        }


}