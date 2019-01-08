package com.example.aizat.course_work.ui.main

import com.example.aizat.course_work.data.model.Spell
import com.example.aizat.course_work.data.model.Todo

sealed class Course
data class CourseItem(val id: Int, val sem_num: Int, val block_in_sem_num: Int,
                      val selected: Boolean, val percents: Int, val name: String,
                      val description: String, val teacher: String, val image_url: String,
                      val todos: List<Todo>, val spells: List<Spell>) : Course()

data class CourseDivider(val name: String) : Course()