package com.example.aizat.course_work.ui.main.course

import android.content.DialogInterface
import com.arellomobile.mvp.InjectViewState
import com.example.aizat.course_work.addSchedulers
import com.example.aizat.course_work.data.repository.StudentRepositoryProvider
import com.example.aizat.course_work.ui.main.Course
import com.example.aizat.course_work.ui.main.CourseDivider
import com.example.aizat.course_work.ui.main.CourseItem
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
                        courses.add(CourseDivider(it.name))
                        it.courses.forEach {
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
                .selectCourse((course as CourseItem).id, true)
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