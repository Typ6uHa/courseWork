package com.example.aizat.course_work.ui.main.profile

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.aizat.course_work.addSchedulers
import com.example.aizat.course_work.data.TokenStorage
import com.example.aizat.course_work.data.model.Course
import com.example.aizat.course_work.data.repository.StudentRepositoryProvider
import io.reactivex.rxkotlin.plusAssign
import one.stride.telegramstories.ui.base.BasePresenter

@InjectViewState
class ProfilePresenter : BasePresenter<ProfileView>(){

    private val courses: MutableList<Course> = mutableListOf()
    private val studentRepository = StudentRepositoryProvider.instance

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable += studentRepository
                .getSelectedCourses()
                .addSchedulers()
                .subscribe{it->
                    courses.addAll(it)
                    viewState.submitList(courses)
                }
    }

    fun onCourseClick(course: Course) {
        viewState.showSkillScreen(course.name,course.todos)
    }

    fun onLogoutClick() {
        TokenStorage.removeToken()
        viewState.showLoginScreen()
    }
}