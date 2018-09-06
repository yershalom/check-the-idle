package com.yershalom.checktheidle.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.yershalom.checktheidle.data.Model
import com.yershalom.checktheidle.PostActivity
import com.yershalom.checktheidle.R

class TopicAdapter internal constructor(private var topics: List<Model.ChildrenData>) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    val TOPIC = "topic"

    class TopicViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var topicTitle: TextView = itemView.findViewById<View>(R.id.tv_title) as TextView
        internal var topicDescription: TextView = itemView.findViewById<View>(R.id.tv_description) as TextView
        internal var topicIcon: ImageView = itemView.findViewById<View>(R.id.iv_icon) as ImageView
        internal var cardView: CardView = itemView.findViewById(R.id.cv_topics) as CardView

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): TopicViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.topic_cardview_activity, viewGroup, false)
        return TopicViewHolder(v)
    }

    override fun onBindViewHolder(topicViewHolder: TopicViewHolder, i: Int) {
        topicViewHolder.topicTitle.text = topics[i].data.display_name
        topicViewHolder.topicDescription.text = topics[i].data.public_description
        if (topics[i].data.icon_img.isNotEmpty()) {
            Picasso.get().load(topics[i].data.icon_img).into(topicViewHolder.topicIcon)
        } else {
            val randomIcon = "https://b.thumbs.redditmedia.com/S6FTc5IJqEbgR3rTXD5boslU49bEYpLWOlh8-CMyjTY.png"
            Picasso.get().load(randomIcon).into(topicViewHolder.topicIcon)
        }
        topicViewHolder.cardView.setOnClickListener { view ->
            val intent = startTopic(view.context ,topics[i].data.display_name)
            view.context.startActivity(intent)
        }
    }

    private fun startTopic(context: Context, topic: String): Intent {
        val intent = Intent(context, PostActivity::class.java)
        intent.putExtra(TOPIC, topic)
        return intent
    }

    override fun getItemCount(): Int {
        return topics.size
    }
}