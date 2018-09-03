package com.yershalom.checktheidle.adapters

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yershalom.checktheidle.Model
import com.yershalom.checktheidle.R
import android.content.Intent
import com.yershalom.checktheidle.WebViewActivity


class PostAdapter internal constructor(private var posts: List<Model.PostsChildrenData>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    val WEBSITE_ADDRESS = "website_address"
    val REDDIT_BASE_URL = "https://reddit.com"

    class PostViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var postTitle: TextView = itemView.findViewById<View>(R.id.tv_posts_title) as TextView
        internal var postScore: TextView = itemView.findViewById<View>(R.id.tv_posts_score) as TextView
        internal var cardView: CardView = itemView.findViewById(R.id.cv_posts) as CardView

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PostViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.posts_cardview_activity, viewGroup, false)
        return PostViewHolder(v)
    }

    override fun onBindViewHolder(postViewHolder: PostViewHolder, i: Int) {
        postViewHolder.postTitle.text = posts[i].data.title
        postViewHolder.postScore.text = posts[i].data.score.toString()
        postViewHolder.cardView.setOnClickListener {
            val browserIntent = Intent(postViewHolder.cardView.context, WebViewActivity::class.java)
            browserIntent.putExtra(WEBSITE_ADDRESS, REDDIT_BASE_URL + posts[i].data.url)
            postViewHolder.cardView.context.startActivity(browserIntent)
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}