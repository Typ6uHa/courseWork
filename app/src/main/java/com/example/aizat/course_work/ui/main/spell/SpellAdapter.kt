package com.example.aizat.course_work.ui.main.spell

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aizat.course_work.R
import com.example.aizat.course_work.data.model.Spell

class SpellAdapter : ListAdapter<Spell, SpellAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_spell, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvSpellName)
        val description: TextView = itemView.findViewById(R.id.tvSpellDescription)
        val level: TextView = itemView.findViewById(R.id.tvLevel)
        fun bind(spell: Spell) {
            name.text = spell.name
            description.text = spell.description
            level.text = "Уровень навыка: ${spell.level}"
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Spell>() {
            override fun areItemsTheSame(oldItem: Spell, newItem: Spell): Boolean {
                return oldItem::class == newItem::class
            }

            override fun areContentsTheSame(oldItem: Spell, newItem: Spell): Boolean {
                return oldItem == newItem
            }
        }
    }
}