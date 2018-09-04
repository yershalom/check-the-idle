package com.yershalom.checktheidle

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.yershalom.checktheidle.adapters.PostAdapter
import com.yershalom.checktheidle.data.Model
import com.yershalom.checktheidle.data.RedditApiService
import kotlinx.android.synthetic.main.posts_recyclerview_activity.*

class PostActivity: AppCompatActivity() {

    val TOPIC = "topic"
    private var posts: List<Model.PostsChildrenData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)

        val topic: String = intent.getStringExtra(TOPIC)
        if (topic.isEmpty()) finish()
        setContentView(R.layout.posts_recyclerview_activity)

        val llm = LinearLayoutManager(this)
        rv_posts.layoutManager = llm
        rv_posts.setHasFixedSize(true)

        initializeData(topic)
        initializeAdapter()
    }

    private fun initializeData(topic: String){
        posts = this.getPosts(topic)
    }

    private fun initializeAdapter(){
        val adapter = PostAdapter(this.posts!!)
        rv_posts.adapter = adapter
    }


    private fun getPosts(topic: String): List<Model.PostsChildrenData>? {
        val client: RedditApiService = RedditApiService.create()
        val some = client.getPosts(topic).execute()
        return some.body()!!.data.children
    }
}