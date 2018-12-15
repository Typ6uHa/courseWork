package com.example.aizat.course_work.ui.auth.login

import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.aizat.course_work.R
import com.example.aizat.course_work.data.TokenStorage
import com.example.aizat.course_work.ui.auth.register.RegisterActivity
import com.example.aizat.course_work.ui.base.BaseActivity
import com.example.aizat.course_work.ui.main.course.CourseActivity
import com.example.aizat.course_work.ui.main.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : BaseActivity(), LoginView {

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (TokenStorage.getToken() != null) {
            showCoursesScreen()
        }
        btnLogin.setOnClickListener {
            if (etEmail.text.isEmpty() || etPassword.text.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                presenter.onLoginClick(etEmail.text.toString(), etPassword.text.toString())
            }
        }

        tvSignup.setOnClickListener {
            presenter.onSignUpClick()
        }
    }

    override fun showCoursesScreen() {
        startActivity<CourseActivity>()
        finish()
    }

    override fun showRegisterScreen() {
        startActivity<RegisterActivity>()
    }

    override fun showErrorToast() {
        Toast.makeText(this, "Неправильные данные", Toast.LENGTH_SHORT).show()
    }
}