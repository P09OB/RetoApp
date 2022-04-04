package com.example.reto1

import android.graphics.Bitmap

class Post {
     var id:String
     var description: String
     var photo: Bitmap
     var date: String
     var city: String

     constructor(id:String, description:String,photo:Bitmap, date:String, city: String){
         this.id = id
         this.description = description
         this.photo = photo
         this.date = date
         this.city = city
     }

 }




