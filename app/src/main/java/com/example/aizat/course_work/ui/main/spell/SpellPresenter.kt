package com.example.aizat.course_work.ui.main.spell

import com.arellomobile.mvp.InjectViewState
import com.example.aizat.course_work.addSchedulers
import com.example.aizat.course_work.data.model.Spell
import com.example.aizat.course_work.data.repository.StudentRepositoryProvider
import io.reactivex.rxkotlin.plusAssign
import one.stride.telegramstories.ui.base.BasePresenter

@InjectViewState
class SpellPresenter : BasePresenter<SpellView>() {

    private var learningSpells: List<Spell> = emptyList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable += StudentRepositoryProvider.instance
                .getLearningSpells()
                .addSchedulers()
                .subscribe { it ->
                    learningSpells = it.filter {
                        it.has == true
                    }
                    viewState.submitList(learningSpells)
                }
    }
}