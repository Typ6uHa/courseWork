package com.example.aizat.course_work.ui.main.skills

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.aizat.course_work.R
import com.example.aizat.course_work.data.model.Todo

class TodoAdapter(private val onCheckBoxClick: (Int, Boolean) -> Unit) : ListAdapter<Todo, TodoAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = ViewHolder(inflater.inflate(R.layout.item_todo, parent, false))
        holder.checkBox.setOnClickListener {
            onCheckBoxClick((getItem(holder.adapterPosition) as Todo).id, holder.checkBox.isChecked)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvTodoName)
        val description: TextView = itemView.findViewById(R.id.tvTodoDescription)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
        fun bind(todo: Todo) {
            name.text = todo.name
            description.text = todo.description
            checkBox.isChecked = todo.checked
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem::class == newItem::class
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem == newItem
            }
        }
    }
}