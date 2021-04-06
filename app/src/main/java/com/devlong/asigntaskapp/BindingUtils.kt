package com.devlong.asigntaskapp

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/*
* Author by Admin on 28/03/21
*/

object BindingUtils {
    //ta ca cac phuong thuc trong nay deu la static
    @JvmStatic
    @BindingAdapter("loadImageInt")
    fun loadImageInt(iv: ImageView, imageId: Int) {
        Glide.with(iv)
            .load(imageId)
            .placeholder(R.drawable.anhdemo)
            .error(R.drawable.anhdemo)
            .into(iv)
    }

    @JvmStatic
    @BindingAdapter("loadImageLink")
    fun loadImageInt(iv: ImageView, link: String?) {
        if (link == null) {
            Glide.with(iv)
                .load(R.drawable.anhdemo)
                .placeholder(R.drawable.anhdemo)
                .error(R.drawable.anhdemo)
                .into(iv)
            return
        }
        Glide.with(iv)
            .load(link)
            .placeholder(R.drawable.anhdemo)
            .error(R.drawable.anhdemo)
            .into(iv)
    }

    @JvmStatic
    @BindingAdapter("setText")
    fun setText(tv: TextView, value: String?) {
        tv.setText(value)
    }

}