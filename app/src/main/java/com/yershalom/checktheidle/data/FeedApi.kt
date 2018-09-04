package com.yershalom.checktheidle.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

interface RedditApiService {

    @GET("subreddits/default.json")
    fun getTopics(): Call<Model.Data>

    @GET("/r/{topic}/hot.json")
    fun getPosts(@Path("topic") topic: String): Call<Model.PostsData>

    companion object {
        fun create(): RedditApiService {
            val BASE_REDDIT_URL = "https://www.reddit.com/"

            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_REDDIT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(RedditApiService::class.java)
        }
    }
}