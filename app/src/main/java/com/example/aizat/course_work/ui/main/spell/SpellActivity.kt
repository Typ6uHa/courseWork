package com.example.aizat.course_work.ui.main.spell

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.aizat.course_work.R
import com.example.aizat.course_work.data.model.Spell
import com.example.aizat.course_work.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_spell.*
import kotlinx.android.synthetic.main.toolbar.*

class SpellActivity : BaseActivity(), SpellView {

    @InjectPresenter
    lateinit var presenter: SpellPresenter

    private val adapter: SpellAdapter = SpellAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun submitList(list: List<Spell>) {
        adapter.submitList(list)
    }
}