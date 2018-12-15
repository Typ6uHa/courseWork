package com.example.aizat.course_work.data.model

import java.io.Serializable

data class Todo(val id: Int, val name: String, val description: String, val checked: Boolean) : Serializable