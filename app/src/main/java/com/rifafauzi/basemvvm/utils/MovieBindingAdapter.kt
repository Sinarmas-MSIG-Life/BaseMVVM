package com.rifafauzi.basemvvm.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rifafauzi.basemvvm.R

/**
 * Created by rrifafauzikomara on 2020-01-03.
 */

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (imgUrl.isNullOrEmpty()) {
        Glide.with(imgView.context)
            .load(R.drawable.ic_no_data)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_error))
            .into(imgView)
    } else {
        Glide.with(imgView.context)
            .load(LINK_IMAGE+imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_error))
            .into(imgView)
    }
}