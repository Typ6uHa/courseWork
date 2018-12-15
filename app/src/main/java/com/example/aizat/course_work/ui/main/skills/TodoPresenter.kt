package com.example.aizat.course_work.ui.main.skills

import com.arellomobile.mvp.InjectViewState
import com.example.aizat.course_work.addSchedulers
import com.example.aizat.course_work.data.model.Todo
import com.example.aizat.course_work.data.repository.StudentRepositoryProvider
import io.reactivex.rxkotlin.plusAssign
import one.stride.telegramstories.ui.base.BasePresenter

@InjectViewState
class TodoPresenter(val name: String, private val todos: List<Todo>, private val idCourse: Int) : BasePresenter<TodoView>() {

    private val studentRepository = StudentRepositoryProvider.instance

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle(name)
        viewState.submitList(todos)
    }

    fun onCheckBoxClick(id: Int, checked: Boolean) {
        disposable += studentRepository
                .selectTodo(id, checked,idCourse)
                .addSchedulers()
                .subscribe()
    }
}