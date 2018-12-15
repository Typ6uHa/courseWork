package com.example.aizat.course_work.data.repository

object StudentRepositoryProvider {
    val instance: StudentRepository by lazy {
        StudentRepositoryImpl()
    }
}