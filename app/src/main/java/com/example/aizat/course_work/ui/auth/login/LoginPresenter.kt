package com.example.aizat.course_work.ui.auth.login

import com.arellomobile.mvp.InjectViewState
import com.example.aizat.course_work.addSchedulers
import com.example.aizat.course_work.data.TokenStorage
import com.example.aizat.course_work.data.model.AuthResponse
import com.example.aizat.course_work.data.repository.StudentRepositoryProvider
import io.reactivex.rxkotlin.plusAssign
import one.stride.telegramstories.ui.base.BasePresenter

@InjectViewState
class LoginPresenter : BasePresenter<LoginView>() {

    fun onLoginClick(email: String, password: String) {
        disposable += StudentRepositoryProvider.instance
                .login(email, password).addSchedulers()
                .onErrorReturn {
                    AuthResponse("null")
                }.subscribe { it ->
                    if (it.token != "null") {
                        TokenStorage.putToken(it.token)
                    }
                    if (TokenStorage.getToken() != null && TokenStorage.getToken() != "null") {
                        viewState.showCoursesScreen()
                    } else {
                        viewState.showErrorToast()
                    }
                }
    }

    fun onSignUpClick() {
        viewState.showRegisterScreen()
    }
}