package com.yershalom.checktheidle

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

interface RedditApiService {

    @GET("subreddits/default.json")
    fun getTopics(): Call<Model.Data>

    @GET("/r/{topic}/hot.json")
    fun getPosts(@Path("topic") topic: String): Call<Model.Posts>

    companion object {
        fun create(): RedditApiService {

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.reddit.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(RedditApiService::class.java)
        }
    }
}