package com.example.aizat.course_work.ui.auth.login

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import one.stride.telegramstories.ui.base.BaseView

interface LoginView : BaseView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showCoursesScreen()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showRegisterScreen()

    fun showErrorToast()
}