package com.example.aizat.course_work.ui.auth.register

import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.aizat.course_work.R
import com.example.aizat.course_work.ui.auth.login.LoginActivity
import com.example.aizat.course_work.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity

class RegisterActivity : BaseActivity(), RegisterView {

    @InjectPresenter
    lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSignup.setOnClickListener {
            if (etEmail.text.isEmpty() || etPassword.text.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                presenter.onSignUpClick(etEmail.text.toString(), etPassword.text.toString())
            }
        }

        tvLogin.setOnClickListener {
            presenter.onLoginClick()
        }
    }

    override fun showLoginScreen() {
        startActivity<LoginActivity>()
    }

    override fun showErrorToast() {
        Toast.makeText(this, "Регистрация не удалась", Toast.LENGTH_SHORT).show()
    }
}