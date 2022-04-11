package com.example.reto1

import android.graphics.Bitmap

class Post {
     var id:String
     var nameUser:String
     var photoUser: Bitmap?
     var description: String
     var photo: Bitmap
     var date: String
     var city: String

     constructor(id:String, nameUser:String, photoUser: Bitmap?, description:String,photo:Bitmap, date:String, city: String){
         this.id = id
         this.nameUser = nameUser
         this.photoUser = photoUser
         this.description = description
         this.photo = photo
         this.date = date
         this.city = city
     }

 }




