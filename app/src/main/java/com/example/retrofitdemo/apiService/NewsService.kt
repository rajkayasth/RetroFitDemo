package com.example.retrofitdemo.apiService

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// https://newsapi.org/v2/everything?q=tesla&from=2022-05-28&sortBy=publishedAt&apiKey=360f79fc21b04883aa6d58f675c76282
// https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY


//const val BASE_URL = "https://newsapi.org/"
//const val BASE_URL2 = "https://jsonplaceholder.typicode.com/"

//const val API_KEY = "360f79fc21b04883aa6d58f675c76282"
private var API_URL = "https://api.apilayer.com/exchangerates_data/"
const val API_KEY = "URYdczv4Ik5SiBw1vFScCkGs5485qIOe"

//singleton retrofit client
class RetrofitsClient {
    companion object {
        private var instance: Api? = null
        private var retroFitInstance: Retrofit? = null


        private fun retroInstance(): Retrofit {

            val headerInterceptor = Interceptor { chain ->
                var request = chain.request()
                request = request.newBuilder()
                    .addHeader("apikey", API_KEY)
                    .build()

                chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60L, unit = TimeUnit.SECONDS)
                .writeTimeout(60L, unit = TimeUnit.SECONDS)
                .connectTimeout(60L, unit = TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)
                .build()

            if (retroFitInstance == null) {
                return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(API_URL)
                    .client(okHttpClient)
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
//intercepter


//
//            val loggingInterceptor = HttpLoggingInterceptor()
//            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

//            val cacheInterceptor = object : Interceptor {
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val response: Response = chain.proceed(chain.request())
//                    val cacheControl = CacheControl.Builder()
//                        .maxAge(1, TimeUnit.DAYS)
//                        .build()
//                    return response.newBuilder()
//                        .header("Cache-Control", cacheControl.toString())
//                        .build()
//                }
//
//            }
//            val headerInterceptor = Interceptor { chain ->
//                var request = chain.request()
//                request = request.newBuilder()
//                    .addHeader("x-device-type", Build.DEVICE)
//                    .build()
//
//                chain.proceed(request)
//            }

//            val okHttpClient = OkHttpClient.Builder()
//                .addNetworkInterceptor(cacheInterceptor)
//                .addInterceptor(headerInterceptor)
//                .addInterceptor(loggingInterceptor)
//                .build()

