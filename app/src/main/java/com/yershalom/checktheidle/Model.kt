package com.yershalom.checktheidle

import com.google.gson.annotations.SerializedName
import kotlin.collections.List

object Model {

    data class Data(
            @SerializedName("data") val data: Children
    )

    data class Children(
            @SerializedName("children") val children: List<ChildrenData>
    )

    data class ChildrenData(
            @SerializedName("data") val data: Topics
    )

    data class Topics(
            @SerializedName("url") val url: String,
            @SerializedName("display_name") val display_name: String,
            @SerializedName("public_description") val public_description: String,
            @SerializedName("icon_img") val icon_img: String
    )

    data class PostsData(
            @SerializedName("data") val data: PostsChildren
    )

    data class PostsChildren(
            @SerializedName("children") val children: List<PostsChildrenData>
    )

    data class PostsChildrenData(
            @SerializedName("data") val data: Posts
    )

    data class Posts(
            @SerializedName("permalink") val url: String,
            @SerializedName("title") val title: String,
            @SerializedName("score") val score: Int
    )
}