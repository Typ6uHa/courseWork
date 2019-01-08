package com.example.aizat.course_work.ui.main.course

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.aizat.course_work.ui.main.Course
import one.stride.telegramstories.ui.base.BaseView

interface CourseView : BaseView {

    fun submitList(list: List<Course>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProfileScreen()

    fun showAcceptPopUp(course: Course)
    fun showLearnedSpellScreen()
    fun showTodoScreen(course: Course)
}