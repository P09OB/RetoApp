package com.example.reto1

import android.media.Image
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //UI Controllers

    var descriptionrow: TextView = itemView.findViewById(R.id.descriptionrow)
    var daterow: TextView = itemView.findViewById(R.id.daterow)
    var cityrow: TextView = itemView.findViewById(R.id.cityrow)
    var imagerow: ImageView = itemView.findViewById(R.id.imagerow)
    var userNamerow: TextView = itemView.findViewById(R.id.userNamerow)
    var photorow : ImageView = itemView.findViewById(R.id.photoUser)


    //State

    init {



    }
}