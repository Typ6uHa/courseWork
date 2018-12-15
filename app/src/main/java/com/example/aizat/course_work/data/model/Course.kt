package com.example.aizat.course_work.data.model

import java.io.Serializable

data class Course(val id: Int, val sem_num: Int, val block_in_sem_num: Int,
                  val selected: Boolean, val percents: Int, val name: String,
                  val description: String, val teacher: String, val image_url: String,
                  val todos: List<Todo>, val spells: List<Spell>) : Serializable