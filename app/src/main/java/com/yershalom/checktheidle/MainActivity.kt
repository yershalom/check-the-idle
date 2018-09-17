package com.yershalom.checktheidle

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.yershalom.checktheidle.adapters.TopicAdapter
import com.yershalom.checktheidle.data.Model
import com.yershalom.checktheidle.data.RedditApiService
import kotlinx.android.synthetic.main.recyclerview_activity.*


class MainActivity : AppCompatActivity() {

    private var topics: List<Model.ChildrenData>? = null
    private lateinit var gridLayoutManager: GridLayoutManager
    private val llm = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_activity)
        gridLayoutManager = GridLayoutManager(this, 2)

        rv_topic.layoutManager = gridLayoutManager
        rv_topic.setHasFixedSize(true)

        initializeData()
        initializeAdapter()

        fab.setOnClickListener {
            ObjectAnimator.ofFloat(fab, "rotation", 0f, 180f).setDuration(500).start()
            toggleView()
        }

        rv_topic.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab.visibility === View.VISIBLE) {
                    fab.hide()
                } else if (dy < 0 && fab.visibility !== View.VISIBLE) {
                    fab.show()
                }
            }
        })
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

    private fun toggleView() {
        if (rv_topic.layoutManager == llm) {
            rv_topic.layoutManager = gridLayoutManager
        } else {
            rv_topic.layoutManager = llm
        }
    }
}
