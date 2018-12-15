package com.example.aizat.course_work.ui.main.skills

import com.example.aizat.course_work.data.model.Spell
import com.example.aizat.course_work.data.model.Todo
import one.stride.telegramstories.ui.base.BaseView

interface TodoView : BaseView {

    fun setTitle(title: String)

    fun submitList(list: List<Todo>)
}