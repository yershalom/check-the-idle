package com.yershalom.checktheidle

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.yershalom.checktheidle.adapters.TopicAdapter
import com.yershalom.checktheidle.data.Model
import com.yershalom.checktheidle.data.RedditApiService
import kotlinx.android.synthetic.main.recyclerview_activity.*


class MainActivity : AppCompatActivity() {

    private var topics: List<Model.ChildrenData>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_activity)

        val llm = LinearLayoutManager(this)
        rv_topic.layoutManager = llm
        rv_topic.setHasFixedSize(true)

        initializeData()
        initializeAdapter()
    }

    private fun initializeData(){
        topics = getTopics()
    }

    private fun getTopics(): List<Model.ChildrenData> {
        val client: RedditApiService = RedditApiService.create()
        val some = client.getTopics().execute()
        return some.body()!!.data.children
    }

    private fun initializeAdapter(){
        val adapter = TopicAdapter(this.topics!!)
        rv_topic.adapter = adapter
    }
}
