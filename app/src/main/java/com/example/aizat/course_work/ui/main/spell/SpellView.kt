package com.example.aizat.course_work.ui.main.spell

import com.example.aizat.course_work.data.model.Spell
import one.stride.telegramstories.ui.base.BaseView

interface SpellView : BaseView {

    fun submitList(list: List<Spell>)
}