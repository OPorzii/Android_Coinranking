package com.piyawat.android_coinranking.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

@BindingAdapter("imageSvgUrl")
fun loadSvgImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        GlideToVectorYou.init().with(view.context).load(Uri.parse(url), view)
    }
}