package com.cryptofuture.app.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
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

        setupBottomNavBar()
    }


    private fun setupBottomNavBar() {
        val navController = getNavController()

        navController?.let {
            setupWithNavController(binding.mainBottomNav, it)

            binding.mainBottomNav.setOnNavigationItemSelectedListener { item ->
                when {
                    item.itemId == R.id.action_dicover && it.currentDestination?.id != R.id.map_fragment ->
                        it.navigate(R.id.map_nav_graph)
                    item.itemId == R.id.action_prediction && it.currentDestination?.id != R.id.prediction_fragment ->
                        it.navigate(R.id.prediction_fragment)
                }
                true
            }
        }
    }

    private fun getNavController() =
        supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)
            ?.findNavController()
}
