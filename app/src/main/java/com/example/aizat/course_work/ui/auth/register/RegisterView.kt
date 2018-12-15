package com.example.aizat.course_work.ui.auth.register

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import one.stride.telegramstories.ui.base.BaseView

interface RegisterView : BaseView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoginScreen()

    fun showErrorToast()
}