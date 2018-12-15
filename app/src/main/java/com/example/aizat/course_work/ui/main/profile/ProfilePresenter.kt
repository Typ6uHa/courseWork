package com.example.aizat.course_work.ui.main.profile

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.aizat.course_work.addSchedulers
import com.example.aizat.course_work.data.TokenStorage
import com.example.aizat.course_work.data.model.Course
import com.example.aizat.course_work.data.model.Todo
import com.example.aizat.course_work.data.repository.StudentRepositoryProvider
import io.reactivex.rxkotlin.plusAssign
import one.stride.telegramstories.ui.base.BasePresenter

@InjectViewState
class ProfilePresenter : BasePresenter<ProfileView>() {

    private val courses: MutableList<Course> = mutableListOf()
    private val studentRepository = StudentRepositoryProvider.instance

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable += studentRepository
                .getSelectedCourses()
                .addSchedulers()
                .subscribe { it ->
                    courses.addAll(it)
                    viewState.submitList(courses)
                }
        subscribeToTodoUpdate()
    }

    fun onCourseClick(course: Course) {
        viewState.showSkillScreen(course.name, course.todos, course.id)
    }

    fun onLogoutClick() {
        TokenStorage.removeToken()
        viewState.showLoginScreen()
    }

    private fun subscribeToTodoUpdate() {
        disposable += studentRepository.getTodoUpdate()
                .addSchedulers()
                .subscribe {
                    viewState.submitList(updateCourseStatus(id = it.idTodo, checked = it.selected, idCourse = it.idCourse))
                }
    }

    private fun updateCourseStatus(id: Int, checked: Boolean, idCourse: Int): List<Course> {
        var listTodos = courses.find { it.id == idCourse }!!.todos
        listTodos = listTodos.map {
            if (it.id == id) {
                it.copy(checked = checked)
            } else {
                it
            }
        }
        return courses.map {
            if (it.id == idCourse) {
                it.copy(todos = listTodos)
            } else {
                it
            }
        }
    }
}