package com.example.retrofitdemo.apiService

import android.os.Build

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

// https://newsapi.org/v2/everything?q=tesla&from=2022-05-28&sortBy=publishedAt&apiKey=360f79fc21b04883aa6d58f675c76282
// https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY


const val BASE_URL = "https://newsapi.org/"
const val BASE_URL2 = "https://jsonplaceholder.typicode.com/"
const val API_KEY = "360f79fc21b04883aa6d58f675c76282"

//singleton retrofit client
class RetrofitsClient {
    companion object {
        private var instance: Api? = null
        private var retroFitInstance: Retrofit? = null

        private fun retroInstance(): Retrofit {


            if (retroFitInstance == null){
                return Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retroFitInstance as Retrofit
        }



        @Synchronized
        fun getInstance(): Api {
            // Log.d("WebAccess", "Creating retrofit client")
            if (instance == null) {

                instance = retroInstance().create(Api::class.java)
            }
            // Log.d("WebAccess", getInstance().toString())
            return instance as Api

        }
    }
}
