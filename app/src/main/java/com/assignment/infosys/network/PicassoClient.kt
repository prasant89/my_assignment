package com.assignment.infosys.network

import android.content.Context
import android.widget.ImageView
import com.assignment.infosys.R

import com.squareup.picasso.Picasso
object PicassoClient {
    fun downloadImage(c: Context?, imageUrl: String?, img: ImageView?) {
        if (imageUrl != null && imageUrl.length > 0) {
            Picasso.get().load(imageUrl).placeholder(R.drawable.ic_broken_image).into(img)
        } else {
            Picasso.get().load(R.drawable.ic_image_place_holder).into(img)
        }
    }
}