package com.example.aizat.course_work.ui.auth.register

import com.arellomobile.mvp.InjectViewState
import com.example.aizat.course_work.addSchedulers
import com.example.aizat.course_work.data.TokenStorage
import com.example.aizat.course_work.data.model.AuthResponse
import com.example.aizat.course_work.data.repository.StudentRepositoryProvider
import io.reactivex.rxkotlin.plusAssign
import one.stride.telegramstories.ui.base.BasePresenter

@InjectViewState
class RegisterPresenter : BasePresenter<RegisterView>() {
    fun onLoginClick() {
        viewState.showLoginScreen()
    }

    fun onSignUpClick(email: String, password: String) {
        disposable += StudentRepositoryProvider.instance
                .register(email, password)
                .addSchedulers()
                .onErrorReturn {
                    AuthResponse("null")
                }
                .subscribe { it ->
                    if(it.token!="null"){
                        TokenStorage.putToken(it.token)
                    }
                    if (TokenStorage.getToken() != null && it.token != "null") {
                        viewState.showLoginScreen()
                    } else {
                        viewState.showErrorToast()
                    }
                }
    }
}