package com.example.aizat.course_work.data.model

import java.io.Serializable

data class Blocks(val id: Int, val name: String, val sem_num: Int, val block_in_sem_num: Int, val courses: List<Course>) : Serializable