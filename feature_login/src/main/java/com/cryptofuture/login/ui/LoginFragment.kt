package com.cryptofuture.login.ui

import com.cryptofuture.login.R
import com.cryptofuture.login.databinding.FragmentLoginBinding
import com.cryptofuture.login.viewmodel.LoginViewModel
import com.cryptofuture.login.viewmodel.LoginViewModel.Event
import com.cryptofuture.londhenet.lib.core.feature.BaseFragment
import com.cryptofuture.londhenet.lib.core.util.observe
import javax.inject.Inject

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onAfterCreated() {
        loginViewModel.apply {
            observe(event) { handleViewModelEvents(it) }
            binding.viewModel = this
        }
        binding.login.setOnClickListener { onLoginClick() }
    }

    private fun onLoginClick() {
        loginViewModel.onLoginClick(
            binding.username.text.toString(),
            binding.password.text.toString()
        )
    }

    private fun handleViewModelEvents(event: Event) {
        when (event) {
            Event.NavigateToMain -> navController.navigateUp()
            is Event.ShowError -> showError(event.message)
            is Event.ShowUserNameError -> showUsernameError()
            is Event.ShowPasswordError -> showPasswordError()
        }
    }

    private fun showPasswordError() {
        binding.password.error = getString(R.string.error_input_password)
    }

    private fun showUsernameError() {
        binding.username.error = getString(R.string.error_input_email)
    }

    private fun showError(message: Int) {

    }
}
