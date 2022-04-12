package com.geekbrains.popularlibraries.ui.login

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.geekbrains.popularlibraries.app
import com.geekbrains.popularlibraries.data.PASSWORD
import com.geekbrains.popularlibraries.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var viewModel: Contract.ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = restoreViewModel()

        binding.loginButton.setOnClickListener {
            viewModel?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }

        binding.registrationButton.setOnClickListener {
            (viewModel as ViewModel).onRegistration()
        }

        binding.forgotPasswordTextView.setOnClickListener {
            viewModel?.onShowPassword()
        }

        viewModel?.shouldShowProgress?.subscribe { shouldShow ->
            if (shouldShow == true) {
                showProgress()
            } else {
                hideProgress()
            }
        }

        viewModel?.isSuccess?.subscribe {
            if (it == true) {
                setSuccess()
            }
        }

        viewModel?.errorText?.subscribe {
            it?.let {
                val success = viewModel?.isSuccess?.value
                if (success == false) {
                    setError(it)
                }
            }
        }

        viewModel?.registration?.subscribe {
            if (it == true) {
                setRegistration()
            }
        }
        viewModel?.helper?.subscribe {
            if (it == true) {
                setPasswordHelper(PASSWORD)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.isSuccess?.unsubscribeAll()
        viewModel?.shouldShowProgress?.unsubscribeAll()
        viewModel?.errorText?.unsubscribeAll()
        viewModel?.registration?.unsubscribeAll()
        viewModel?.helper?.unsubscribeAll()
    }

    private fun restoreViewModel(): ViewModel {
        val viewModel = lastCustomNonConfigurationInstance as? ViewModel
        return viewModel ?: ViewModel(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }

    private fun setSuccess() {
        binding.loginButton.isVisible = false
        binding.loginEditText.isVisible = false
        binding.passwordEditText.isVisible = false
        binding.registrationButton.isVisible = false
        binding.forgotPasswordTextView.isVisible = false
        binding.root.setBackgroundColor(Color.GREEN)
    }

    private fun setError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress() {
        binding.loginButton.isEnabled = false
        binding.registrationButton.isEnabled = false
        hideKeyboard(this)
    }

    private fun hideProgress() {
        binding.loginButton.isEnabled = true
        binding.registrationButton.isEnabled = true
    }

    private fun setRegistration() {
        binding.loginButton.isVisible = false
        binding.forgotPasswordTextView.isVisible = false
        binding.root.setBackgroundColor(Color.BLUE)
    }

    private fun setPasswordHelper(PASSWORD: String) {
        Toast.makeText(this, PASSWORD, Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}