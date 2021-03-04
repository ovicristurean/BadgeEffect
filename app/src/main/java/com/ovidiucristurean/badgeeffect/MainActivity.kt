package com.ovidiucristurean.badgeeffect

import android.os.Bundle
import com.ovidiucristurean.app_commons.BindingActivity
import com.ovidiucristurean.badgeeffect.databinding.ActivityMainBinding

class MainActivity : BindingActivity<ActivityMainBinding>() {

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}