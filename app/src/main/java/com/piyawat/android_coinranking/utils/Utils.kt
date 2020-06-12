package com.piyawat.android_coinranking.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


@BindingAdapter("imageSvgUrl")
fun loadSvgImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
//        Glide.with(view.context).load(url).into(view)
        GlideToVectorYou.init().with(view.context).load(Uri.parse(url), view)
    }
}