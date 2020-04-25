package com.shaquibquraishi.kotlindemo

import android.app.Application
import com.shaquibquraishi.kotlindemo.data.network.ApolloConnector
import com.shaquibquraishi.kotlindemo.data.network.NetworkConnectionInterceptor
import com.shaquibquraishi.kotlindemo.data.repository.UserRepository
import com.shaquibquraishi.kotlindemo.data.sharedPref.SharedPreferencesMethod
import com.shaquibquraishi.kotlindemo.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApolloConnector(instance()) }
        bind() from singleton { SharedPreferencesMethod(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }

}