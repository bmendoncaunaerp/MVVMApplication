package com.example.mvvmapplication.login.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvvmapplication.R
import com.example.mvvmapplication.databinding.ActivityLoginBinding
import com.example.mvvmapplication.fact.presentation.FactActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiAction.collect { action ->
                    executeAction(action)
                }
            }
        }

        viewModel.onViewCreated()

        with(binding) {
            btnLogin.setOnClickListener {
                viewModel.onLoginClicked(getEmailText(), getPasswordText())
            }

            btnCreateAccount.setOnClickListener {
                viewModel.onCreateAccountClicked(getEmailText(), getPasswordText())
            }

            btnRecoverPassword.setOnClickListener {
                viewModel.onRecoverPasswordClicked(getEmailText())
            }
        }
    }

    private fun executeAction(action: LoginAction) {
        when(action) {
            LoginAction.NAVIGATE_HOME -> navigateHome()
            LoginAction.SHOW_ERROR_MSG -> showMessage("An error occurred. Try again.")
            LoginAction.SHOW_RECOVER_MSG -> showMessage("Check your email.")
        }
    }

    private fun navigateHome() {
        val intent = Intent(this, FactActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun getEmailText() =
        binding.edtEmail.text.toString()

    private fun getPasswordText() =
        binding.edtPassword.text.toString()
}