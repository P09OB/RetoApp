package com.example.reto1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class PostsAdapter: RecyclerView.Adapter<PostViewHolder>() {

    private val posts = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.postrow, parent, false)
        val postViewHolder = PostViewHolder(view)
        return postViewHolder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.descriptionrow.text = post.description
        holder.daterow.text = post.date
        holder.cityrow.text = post.city
        holder.imagerow.setImageBitmap(post.photo)
        holder.userNamerow.text = post.nameUser
        holder.photorow.setImageBitmap(post.photoUser)


    }

    fun adapterPost(post: Post){
        posts.add(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }


}