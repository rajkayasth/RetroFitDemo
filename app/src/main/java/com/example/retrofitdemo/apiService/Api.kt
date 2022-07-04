package com.example.retrofitdemo.apiService

import com.example.retrofitdemo.models.*
import retrofit2.Call
import retrofit2.http.*

interface Api {


    @GET("timeseries")
    fun getData(@Query("start_date") start_date:String ,
                @Query("end_date") end_date :String ,
                @Query("base") base: String
                ) :
            Call<APIData>

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
    ): Call<UserPost>

    @Multipart
    @POST("photos")
    fun sendPhotos(
        @Part("albumID", encoding = "8-bit") albumID: Int,
        @Part("id", encoding = "8-bit") id: Int,
        @Part("title", encoding = "8-bit") title: String,
        @Part("url", encoding = "8-bit") url: String,
        @Part("thumbnailURL", encoding = "8-bit") thumbnailURL: String,
    ): Call<UserData>

    @DELETE("posts/{id}")
    fun delete(
        @Path("id") id :Int
    ): Call<UserPost>

    @PUT("posts/{id}")
    fun put(
        @Path("id")id:Int,
        @Body userPost: UserPost
    ): Call<UserPost>
}