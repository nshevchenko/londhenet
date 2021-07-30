package com.example.newapp.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.newapp.R
import com.example.newapp.databinding.ActivityMainBinding
import com.example.newapp.lib.core.feature.BaseActivity
import com.example.newapp.main.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
