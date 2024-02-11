package ru.netology.linkedout.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import ru.netology.linkedout.R

fun ImageView.loadAvatar(url : String, vararg transforms: BitmapTransformation = emptyArray()) =

    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.avatar_placeholder)
        .error(R.drawable.baseline_error_outline_24)
        .timeout(10_000)
        .transform(*transforms)
        .into(this)

fun ImageView.loadImage(url: String, vararg transforms : BitmapTransformation = emptyArray()) =
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.baseline_image_24)
        .timeout(10_000)
        .transform(*transforms)
        .error(R.drawable.baseline_error_outline_24)
        .into(this)

fun ImageView.loadCircleCrop(url: String, vararg transforms: BitmapTransformation = emptyArray()) =
    loadAvatar(url, CircleCrop(), *transforms)
