package com.cryptofuture.londhenet.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.cryptofuture.londhenet.R
import com.cryptofuture.londhenet.databinding.ActivityMainBinding
import com.cryptofuture.londhenet.lib.core.feature.BaseActivity
import com.cryptofuture.londhenet.main.viewmodel.MainViewModel
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
