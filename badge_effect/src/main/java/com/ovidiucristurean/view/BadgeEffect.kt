package com.ovidiucristurean.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.databinding.BindingAdapter
import com.ovidiucristurean.app_commons.view.CustomView
import com.ovidiucristurean.badge_effect.R
import com.ovidiucristurean.badge_effect.databinding.ViewBadgeEffectBinding

class BadgeEffect : CustomView<ViewBadgeEffectBinding> {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun getLayoutResId(): Int = R.layout.view_badge_effect
}

@set:BindingAdapter("imageDrawable")
var BadgeEffect.imageDrawable: Drawable?
    get() = mBinding.imageDrawable
    set(value) {
        mBinding.imageDrawable = value
        //mBinding.imageIv.setImageResource(R.drawable.ic_round_checkmark)
    }