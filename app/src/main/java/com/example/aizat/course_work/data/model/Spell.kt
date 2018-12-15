package com.example.aizat.course_work.data.model

import java.io.Serializable

data class Spell(val id: Int, val name: String, val level: Int, val description: String, val has: Boolean) : Serializable
