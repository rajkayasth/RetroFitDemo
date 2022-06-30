package com.example.retrofitdemo.apiService

import android.util.Log
import com.example.retrofitdemo.models.News
import com.example.retrofitdemo.models.UserData
import com.example.retrofitdemo.models.UserPost
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

// https://newsapi.org/v2/everything?q=tesla&from=2022-05-28&sortBy=publishedAt&apiKey=360f79fc21b04883aa6d58f675c76282
// https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY


const val BASE_URL = "https://newsapi.org/"
const val BASE_URL2 = "https://jsonplaceholder.typicode.com/"
const val API_KEY = "360f79fc21b04883aa6d58f675c76282"

interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadline(@Query("country") country: String, @Query("page") page: Int): Call<News>



    //https://jsonplaceholder.typicode.com/posts
    @FormUrlEncoded
    @POST("posts")
    fun sendUserData(
//      @Body userPost: UserPost
    @Field("id")id: Int,
    @Field("userId")userId: Int,
    @Field("title")title: String,
    @Field("body")body: String
    ):Call<UserPost>

    @Multipart
    @POST("photos")
    fun sendPhotos(
        @Part("albumID", encoding = "8-bit") albumID: Int,
        @Part("id", encoding = "8-bit") id: Int,
        @Part("title", encoding = "8-bit") title: String,
        @Part("url", encoding = "8-bit") url: String,
        @Part("thumbnailURL", encoding = "8-bit") thumbnailURL: String,
    ):Call<UserData>

    @DELETE("posts/{id}")
    fun delete(
        @Path("id") id :Int
    ):Call<UserPost>

    @PUT("posts/{id}")
    fun put(
        @Path("id")id:Int,
        @Body userPost: UserPost
    ):Call<UserPost>
}

//singleton retrofit client
class RetrofitsClient {
    companion object {
        private var instance : Api? = null
        private var retroFitInstance : Retrofit? = null

        fun retroInstance() : Retrofit{
            if (retroFitInstance ==  null){
                return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL2)
                .build()
            }
                return retroFitInstance as Retrofit
        }

        @Synchronized
        fun getInstance(): Api {
           // Log.d("WebAccess", "Creating retrofit client")
            if (instance == null){

                instance = retroInstance().create(Api::class.java)
            }
           // Log.d("WebAccess", getInstance().toString())
            return instance as Api

        }
    }
}
