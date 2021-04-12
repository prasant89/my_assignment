package com.assignment.infosys.viewmodel

import android.app.Application
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import com.assignment.infosys.data.Row
import com.assignment.infosys.network.SingleLiveData
import com.assignment.infosys.repository.NewsRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = NewsRepository(application)
    fun observeNewsResponce(): SingleLiveData<List<Row>> {
        return repository.newsApiRequest()
    }

    fun observeNewsTitleResponce(): SingleLiveData<String> {
        return repository.newsApiRequestTitle()
    }

    @BindingAdapter("imageHref")
    fun loadimage(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.getContext()).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(imageView)
        //Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }
}