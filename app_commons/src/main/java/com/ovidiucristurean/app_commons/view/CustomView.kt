package com.ovidiucristurean.app_commons.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ovidiucristurean.app_commons.LayoutView
import com.ovidiucristurean.app_core.CoreApp

abstract class CustomView<DataBinding:ViewDataBinding>:FrameLayout, LayoutView {

    lateinit var mBinding: DataBinding

    constructor(context: Context) : super(context) {
        inflateView(context)
    }

    val Int.px: Int
        get() = (this / CoreApp.get().resources.displayMetrics.density).toInt()

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        inflateView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflateView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        inflateView(context)
    }

    override fun getRootView(): View {
        return mBinding.root
    }

    @CallSuper
    open fun onViewCreated() {
        // Override if needed, make sure to call super
        isEnabled = isEnabled // Set the initial state of the view
    }

    @SuppressLint("NewApi")
    fun changeTextViewAppearance(view: TextView, styleId: Int, useCustomPadding: Boolean = true) {
        if (styleId != 0) {
            // Because Calligraphy font path doesn't work with custom views
            // We must extract the path from style item, get it from assets and manually set it
            val typedArray = context.obtainStyledAttributes(styleId, intArrayOf(android.R.attr.paddingTop, android.R.attr.paddingBottom, android.R.attr.paddingStart, android.R.attr.paddingEnd))
            val fontPath = typedArray.getString(0)
            if (useCustomPadding) {
                setCustomPadding(view)
            }
            typedArray.recycle()
            view.setTextAppearance(styleId)
        }
    }

    private fun inflateView(context: Context) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutResId(), this, false)
        addView(rootView)
        onViewCreated()
    }

    private fun setCustomPadding(view: TextView) {
        val valueInfoPaddingTop = view.paddingTop.px
        val valueInfoPaddingBottom = view.paddingBottom.px
        val valueInfoPaddingStart = view.paddingStart.px
        val valueInfoPaddingEnd = view.paddingEnd.px
        view.setPadding(valueInfoPaddingStart, valueInfoPaddingTop, valueInfoPaddingEnd, valueInfoPaddingBottom)
    }
}