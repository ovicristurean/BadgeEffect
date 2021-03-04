package com.ovidiucristurean.app_commons

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingActivity<DataBinding : ViewDataBinding> : AppCompatActivity() {

    abstract fun getLayoutResId(): Int

    protected lateinit var mBinding: DataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            getLayoutResId(),
            null,
            false
        )
    }
}