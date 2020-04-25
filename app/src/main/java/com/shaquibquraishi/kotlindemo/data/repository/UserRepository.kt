package com.shaquibquraishi.kotlindemo.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toDeferred
import com.example.LoginMutation
import com.shaquibquraishi.kotlindemo.data.model.User
import com.shaquibquraishi.kotlindemo.data.network.ApolloConnector
import com.shaquibquraishi.kotlindemo.data.sharedPref.SharedPreferencesMethod


class UserRepository(
    private val apolloConnector: ApolloConnector,
    private val sharedPref: SharedPreferencesMethod
) {

    suspend fun userLogin(email: String, password: String): Response<LoginMutation.Data> {
        val mutation = apolloConnector.setupApollo().mutate(LoginMutation(email, password))
        val deffered = mutation.toDeferred()
        return deffered.await()
    }

    fun saveUserDetails(token: String?, id: String?, email: String?, name: String?) {
        sharedPref.setString(sharedPref.TOKEN, token)
        sharedPref.setString(sharedPref.USER_ID, id)
        sharedPref.setString(sharedPref.USER_EMAIL, email)
        sharedPref.setString(sharedPref.USER_NAME, name)
    }

    fun getUserDetails(): LiveData<User> {
        val token = sharedPref.getString(sharedPref.TOKEN)
        val id = sharedPref.getString(sharedPref.USER_ID)
        val email = sharedPref.getString(sharedPref.USER_EMAIL)
        val name = sharedPref.getString(sharedPref.USER_NAME)
        val user = User(token, id, email, name)
        return MutableLiveData(user)
    }

    fun userLogout() = sharedPref.clear()


}