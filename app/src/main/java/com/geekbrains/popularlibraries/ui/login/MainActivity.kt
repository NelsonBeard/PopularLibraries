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
import com.geekbrains.popularlibraries.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Contract.View {
    private lateinit var binding: ActivityMainBinding
    private var presenter: Contract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = restorePresenter()
        presenter?.onAttach(this)

        binding.loginButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }

        binding.registrationButton.setOnClickListener {
            presenter?.onRegistration()
        }

        binding.forgotPasswordTextView.setOnClickListener{
            presenter?.onShowPassword()
        }
    }

    private fun restorePresenter(): Presenter {
        val presenter = lastCustomNonConfigurationInstance as? Presenter
        return presenter ?: Presenter(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun setSuccess() {
        binding.loginButton.isVisible = false
        binding.loginEditText.isVisible = false
        binding.passwordEditText.isVisible = false
        binding.registrationButton.isVisible = false
        binding.forgotPasswordTextView.isVisible = false
        binding.root.setBackgroundColor(Color.GREEN)
    }

    override fun setError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.loginButton.isEnabled = false
        hideKeyboard(this)
    }

    override fun hideProgress() {
        binding.loginButton.isEnabled = true
    }

    override fun setRegistration() {
        binding.loginButton.isVisible = false
        binding.forgotPasswordTextView.isVisible = false
        binding.root.setBackgroundColor(Color.BLUE)
    }

    override fun setPasswordHelper(PASSWORD: String) {
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