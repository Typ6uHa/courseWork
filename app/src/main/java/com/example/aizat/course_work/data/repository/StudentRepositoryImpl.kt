package com.example.aizat.course_work.data.repository

import com.example.aizat.course_work.data.ApiFactory
import com.example.aizat.course_work.data.model.AuthResponse
import com.example.aizat.course_work.data.model.Blocks
import com.example.aizat.course_work.data.model.Course
import com.example.aizat.course_work.data.model.Spell
import com.example.aizat.course_work.data.repository.model.SpellUpdate
import com.example.aizat.course_work.data.service.StudentService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class StudentRepositoryImpl : StudentRepository {

    private val studentService: StudentService = ApiFactory.getRetrofitService(StudentService::class)
    private val spellUpdatesSubject: PublishSubject<SpellUpdate> = PublishSubject.create()

    override fun login(login: String, password: String): Single<AuthResponse> {
        return studentService.login(login, password)
    }

    override fun register(login: String, password: String): Single<AuthResponse> {
        return studentService.register(login, password)
    }

    override fun getBlocks(): Single<List<Blocks>> {
        return studentService.getBlocks()
    }

    override fun getSelectedCourses(): Single<List<Course>> {
        return studentService.getSelectedCourses()
    }

    override fun getLearningSpells(): Single<List<Spell>> {
        return studentService.getLearningSpells()
    }

    override fun selectCourse(id: Int, selected: Boolean): Completable {
        return studentService.selectCourse(id, selected)
    }

    override fun selectTodo(id: Int, selected: Boolean, idCourse: Int): Completable {
        spellUpdatesSubject.onNext(SpellUpdate(id, selected, idCourse))
        return studentService.selectTodo(id, selected)
    }

    override fun getTodoUpdate(): Observable<SpellUpdate> {
        return spellUpdatesSubject
    }
}