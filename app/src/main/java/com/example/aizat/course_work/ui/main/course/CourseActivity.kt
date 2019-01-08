package com.example.aizat.course_work.ui.main.course

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.aizat.course_work.R
import com.example.aizat.course_work.ui.base.BaseActivity
import com.example.aizat.course_work.ui.main.Course
import com.example.aizat.course_work.ui.main.CourseAdapter
import com.example.aizat.course_work.ui.main.CourseItem
import com.example.aizat.course_work.ui.main.profile.ProfileActivity
import com.example.aizat.course_work.ui.main.skills.TodoActivity
import com.example.aizat.course_work.ui.main.spell.SpellActivity
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class CourseActivity : BaseActivity(), CourseView {

    @InjectPresenter
    lateinit var presenter: CoursePresenter

    private val adapter: CourseAdapter = CourseAdapter({
        presenter.onSelectedItemClick(it)
    }, {
        presenter.onItemClick(it)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        setSupportActionBar(toolbar)
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun submitList(list: List<Course>) {
        adapter.submitList(list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.profile -> presenter.onProfileClick()
            R.id.skill -> presenter.onSkillClick()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showProfileScreen() {
        startActivity<ProfileActivity>()
    }

    override fun showLearnedSpellScreen() {
        startActivity<SpellActivity>()
    }

    override fun showAcceptPopUp(course: Course) {
        val builder = AlertDialog.Builder(this)
        var spells = ""
        (course as CourseItem).spells.forEach {
            spells = spells.plus(it.name + "\n")
        }
        builder.setMessage("Вы хотите записаться на курс: ${course.name}? \n" +
                "Вами будет изучено: \n $spells")
                .setPositiveButton("Да") { _, _ ->
                    presenter.onYesClick(course)
                    // костыль но мне лень делать.
                    finish()
                    startActivity(intent)
                }.setNegativeButton("Нет") { _, _ ->
                }.show()
    }

    override fun showTodoScreen(course: Course) {
        finish()
        startActivity(intentFor<TodoActivity>("name" to (course as CourseItem), "todos" to course.todos))
    }
}