package com.example.aizat.course_work.ui.main.course

import android.content.DialogInterface
import com.arellomobile.mvp.InjectViewState
import com.example.aizat.course_work.addSchedulers
import com.example.aizat.course_work.data.model.Course
import com.example.aizat.course_work.data.model.Spell
import com.example.aizat.course_work.data.repository.StudentRepositoryProvider
import io.reactivex.rxkotlin.plusAssign
import one.stride.telegramstories.ui.base.BasePresenter

@InjectViewState
class CoursePresenter : BasePresenter<CourseView>() {

    private val courses: MutableList<Course> = mutableListOf()
    private val studentRepository = StudentRepositoryProvider.instance

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable += studentRepository
                .getBlocks()
                .addSchedulers()
                .subscribe { it ->
                    it.forEach {
                        courses.addAll(it.courses)
                    }
                    viewState.submitList(courses)
                }
    }

    fun onProfileClick() {
        viewState.showProfileScreen()
    }

    fun onItemClick(course: Course) {
        viewState.showAcceptPopUp(course)
    }

    fun onYesClick(course: Course): DialogInterface.OnClickListener? {
        disposable += studentRepository
                .selectCourse(course.id, true)
                .addSchedulers()
                .subscribe()
        return null
    }

    fun onSkillClick() {
       viewState.showLearnedSpellScreen()
    }

    fun onSelectedItemClick(course: Course) {
        viewState.showTodoScreen(course)
    }
}