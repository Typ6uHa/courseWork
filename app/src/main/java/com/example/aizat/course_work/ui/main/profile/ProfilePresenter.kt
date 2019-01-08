package com.example.aizat.course_work.ui.main.profile

import com.arellomobile.mvp.InjectViewState
import com.example.aizat.course_work.addSchedulers
import com.example.aizat.course_work.data.TokenStorage
import com.example.aizat.course_work.data.repository.StudentRepositoryProvider
import com.example.aizat.course_work.ui.main.Course
import com.example.aizat.course_work.ui.main.CourseItem
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
                    it.forEach {
                        courses.add(CourseItem(
                                id = it.id,
                                sem_num = it.sem_num,
                                block_in_sem_num = it.block_in_sem_num,
                                selected = it.selected,
                                percents = it.percents,
                                name = it.name,
                                description = it.description,
                                teacher = it.teacher,
                                image_url = it.image_url,
                                todos = it.todos,
                                spells = it.spells
                        ))
                    }
                    viewState.submitList(courses)
                }
    }

    fun onCourseClick(course: Course) {
        viewState.showSkillScreen((course as CourseItem).name, course.todos)
    }

    fun onLogoutClick() {
        TokenStorage.removeToken()
        viewState.showLoginScreen()
    }
}