package com.example.aizat.course_work.ui.main.skills

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.aizat.course_work.R
import com.example.aizat.course_work.data.model.Course
import com.example.aizat.course_work.data.model.Todo
import com.example.aizat.course_work.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.toolbar.*

class TodoActivity : BaseActivity(), TodoView {

    @InjectPresenter
    lateinit var presenter: TodoPresenter

    @ProvidePresenter
    fun providePresenter(): TodoPresenter {
        return TodoPresenter(
                intent.getSerializableExtra("name") as String,
                intent.getSerializableExtra("todos") as List<Todo>,
                intent.getIntExtra("id", -1)
        )
    }

    private val adapter: TodoAdapter = TodoAdapter { id, checked ->
        presenter.onCheckBoxClick(id, checked)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun submitList(list: List<Todo>) {
        adapter.submitList(list)
    }
}