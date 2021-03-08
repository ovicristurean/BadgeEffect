package com.ovidiucristurean.app_commons.binding

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.library.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.bumptech.glide.signature.ObjectKey

@BindingAdapter(value = ["android:src", "android:tint"], requireAll = false)
fun loadImageFromResource(imageView: ImageView, drawable: Drawable?, tint: Int = 0) {
    if (drawable != null) {
        val clonedDrawable = drawable.mutate()
        if (tint != 0) {
            clonedDrawable.setColorFilter(tint, PorterDuff.Mode.SRC_ATOP)
        }

        val requestOptions = RequestOptions()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .dontAnimate()
        Glide.with(imageView.context).load(clonedDrawable).apply(requestOptions).into(imageView)

    } else if (tint != 0) {
        imageView.setColorFilter(tint, PorterDuff.Mode.SRC_ATOP)
    }
}

@BindingAdapter(
    value = ["imageUrl", "placeholder", "keepOriginalSize", "doNotAnimate"],
    requireAll = false
)
fun loadImageFromUrl(
    imageView: ImageView, url: String?, placeholder: Drawable?,
    keepOriginalSize: Boolean = false, doNotAnimate: Boolean = false
) {
    var requestBuilder = Glide.with(imageView.context).asBitmap().load(url)
    if (!doNotAnimate) {
        requestBuilder = requestBuilder.transition(BitmapTransitionOptions.withCrossFade())
    }
    var requestOptions = RequestOptions()
    if (keepOriginalSize) {
        requestOptions = requestOptions.override(SIZE_ORIGINAL, SIZE_ORIGINAL)
    }
    if (imageView.scaleType != ImageView.ScaleType.FIT_XY) {
        requestOptions = requestOptions.centerCrop()
    }
    // This is just a workaround to temporary fix a bug where no new images are loaded. It will be loaded on the app update to a new
    // version.
    requestOptions = requestOptions.signature(ObjectKey(com.ovidiucristurean.app_commons.BuildConfig.VERSION_CODE))
    if (placeholder != null) {
        requestOptions =
            requestOptions.placeholder(placeholder).error(placeholder).fallback(placeholder)
    }
    requestBuilder.apply(requestOptions).into(imageView)
}

@BindingAdapter(value = ["src", "foregroundTint"], requireAll = false)
fun setImageResource(imageView: ImageView, drawable: Drawable?, colorStateList: ColorStateList?) {
    val src = drawable ?: imageView.drawable
    if (src != null) {
        val tintedDrawable = DrawableCompat.wrap(src)
        DrawableCompat.setTintList(tintedDrawable, colorStateList)
        imageView.setImageDrawable(tintedDrawable)
    }
}

@BindingAdapter(value = ["srcRes"])
fun setImageResource(imageView: ImageView, imageRes: Int) {
    if (imageRes != 0) {
        imageView.setImageResource(imageRes)
    }
}