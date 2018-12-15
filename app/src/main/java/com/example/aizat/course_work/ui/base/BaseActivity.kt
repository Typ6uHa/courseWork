package com.example.aizat.course_work.ui.base

import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import one.stride.telegramstories.ui.base.BaseView

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}