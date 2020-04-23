package com.shaquibquraishi.kotlindemo.data.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import okhttp3.OkHttpClient


object ApolloConnector {
    private const val BASE_URL = "http://192.168.43.59:4000/graphql"
    fun setupApollo(): ApolloClient {

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        return ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .subscriptionTransportFactory(
                WebSocketSubscriptionTransport.Factory(
                    BASE_URL,
                    okHttpClient
                )
            )
            .build()
    }
}
