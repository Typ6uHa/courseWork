package com.example.aizat.course_work.data.repository

import com.example.aizat.course_work.data.model.AuthResponse
import com.example.aizat.course_work.data.model.Blocks
import com.example.aizat.course_work.data.model.Course
import com.example.aizat.course_work.data.model.Spell
import io.reactivex.Completable
import io.reactivex.Single

interface StudentRepository {

    fun login(login: String, password: String): Single<AuthResponse>

    fun register(login: String, password: String): Single<AuthResponse>

    fun getBlocks(): Single<List<Blocks>>

    fun getSelectedCourses(): Single<List<Course>>

    fun getLearningSpells(): Single<List<Spell>>

    fun selectCourse(id: Int, selected: Boolean): Completable

    fun selectTodo(id: Int, selected: Boolean): Completable
}