package com.yershalom.checktheidle

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent



class MainActivity : AppCompatActivity() {

    private val TOPIC = "topic"

    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topics: List<Model.ChildrenData> = this.getTopics()
        for (topic: Model.ChildrenData in topics) {
            Log.d("topic!!!!!!!!!!!!!!!!!!", topic.toString())
            tv_title.text = topic.data.display_name
            tv_description.text = topic.data.public_description
            if (topic.data.icon_img.isNotEmpty()) {
                Picasso.get().load(topic.data.icon_img).into(iv_icon)
            } else {
                val randomIcon = "https://b.thumbs.redditmedia.com/S6FTc5IJqEbgR3rTXD5boslU49bEYpLWOlh8-CMyjTY.png"
                Picasso.get().load(randomIcon).into(iv_icon)
            }

            card_view.setOnClickListener {
                val intent = this.startTopic(topic.data.url)
                startActivity(intent)
            }
        }
    }

    private fun startTopic(topic: String): Intent {
        val intent = Intent(this, TopicActivity::class.java)
        intent.putExtra(TOPIC, topic)
        return intent
    }

    private fun getTopics(): List<Model.ChildrenData> {
        val client: RedditApiService = RedditApiService.create()
        val some = client.getTopics().execute()
        val body = some.body()!!.data.children
        return body
    }
}
