/*
 * Copyright (c) DAR Ecosystem 2018.
 *
 * Created by sough on 15/08/2018.
 */

package kz.batana.lab3.core

import kz.batana.khanproject.Logger
import kz.batana.lab3.core.errorHandler.RxErrorHandlingCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This module provides all API dependencies
 */
val coreModule = module {
    single { createOkHttpClient() }
}

/**
 * Creates singleton okHttp client
 */
fun createOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Logger.api(message) })
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
}

/**
 * Creates Service from okHttp client
 */
inline fun <reified T> createService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}