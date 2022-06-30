package com.example.retrofitdemo.apiService

import android.util.*
import android.util.Base64

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrofitClient {
    //"raj:123456".toByteArray(),Base64.NO_WRAP
   // private val AUTH = "Basic"+ Base64.encodeToString("raj:123456".toByteArray(),Base64.NO_WRAP)
    private const val BASE_URLS = "https://jsonplaceholder.typicode.com/"

//    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor {
//            chain ->
//            val original = chain.request()
//            val requestBuilder = original.newBuilder()
//                .addHeader("Authorization","")
//                .method(original.method(),original.body())
//
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }.build()

    val instance : Api by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URLS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Api::class.java)
    }
}