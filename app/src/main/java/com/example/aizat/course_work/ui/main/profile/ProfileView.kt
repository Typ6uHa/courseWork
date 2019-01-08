package com.example.aizat.course_work.ui.main.profile

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.aizat.course_work.data.model.Todo
import com.example.aizat.course_work.ui.main.Course
import one.stride.telegramstories.ui.base.BaseView

interface ProfileView : BaseView {

    fun submitList(list: List<Course>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showSkillScreen(name: String, todos: List<Todo>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoginScreen()
}