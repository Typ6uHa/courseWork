package com.example.aizat.course_work.data.service

import com.example.aizat.course_work.data.model.AuthResponse
import com.example.aizat.course_work.data.model.Blocks
import com.example.aizat.course_work.data.model.Course
import com.example.aizat.course_work.data.model.Spell
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.*

interface StudentService {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("login") login: String, @Field("password") password: String): Single<AuthResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(@Field("login") login: String, @Field("password") password: String): Single<AuthResponse>

    @GET("blocks")
    fun getBlocks(): Single<List<Blocks>>

    @GET("student_api/selected_courses")
    fun getSelectedCourses(): Single<List<Course>>

    @GET("student_api/my_learning_spells")
    fun getLearningSpells(): Single<List<Spell>>

    @FormUrlEncoded
    @PUT("student_api/courses/{id}")
    fun selectCourse(@Path("id") id: Int, @Field("selected") selected: Boolean): Completable

    @FormUrlEncoded
    @PUT("student_api/todos/{id}")
    fun selectTodo(@Path("id") id: Int, @Field("selected") selected: Boolean): Completable
}