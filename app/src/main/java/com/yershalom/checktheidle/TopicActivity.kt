package com.yershalom.checktheidle

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.util.Log

class TopicActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)

        val posts: Model.Posts? = this.getPosts("sports")
        Log.d("posts", posts.toString())
    }

    private fun getPosts(topic: String): Model.Posts? {
        Log.d("id", topic)
        val client: RedditApiService = RedditApiService.create()
        val some = client.getPosts(topic).execute()
        val body = some.body()
        Log.d("body!!!fasdf!", body.toString())
        return body
    }
}