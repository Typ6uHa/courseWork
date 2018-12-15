package com.example.aizat.course_work.ui.main.profile

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.aizat.course_work.R
import com.example.aizat.course_work.data.model.Course
import com.example.aizat.course_work.data.model.Todo
import com.example.aizat.course_work.ui.auth.login.LoginActivity
import com.example.aizat.course_work.ui.base.BaseActivity
import com.example.aizat.course_work.ui.main.CourseAdapter
import com.example.aizat.course_work.ui.main.skills.TodoActivity
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.*

class ProfileActivity : BaseActivity(), ProfileView {

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    private val adapter: CourseAdapter = CourseAdapter {
        presenter.onCourseClick(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun submitList(list: List<Course>) {
        adapter.submitList(list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.logout -> presenter.onLogoutClick()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoginScreen() {
        startActivity(intentFor<LoginActivity>().clearTask().newTask())
    }

    override fun showSkillScreen(name: String, todos: List<Todo>) {
        startActivity(intentFor<TodoActivity>("name" to name, "todos" to todos))
    }
}