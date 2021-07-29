package com.example.newapp

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.newapp.lib.core.feature.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
