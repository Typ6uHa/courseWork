package com.example.aizat.course_work.ui.main

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.aizat.course_work.R
import com.example.aizat.course_work.data.model.Course
import com.squareup.picasso.Picasso

class CourseAdapter(private val onItemClick: (Course) -> Unit) : ListAdapter<Course, CourseAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = ViewHolder(inflater.inflate(R.layout.item_course, parent, false))
        holder.itemView.setOnClickListener {
            onItemClick(getItem(holder.adapterPosition))
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.tvCourseName)
        val description: TextView = itemView.findViewById(R.id.tvDescription)
        val icon: ImageView = itemView.findViewById(R.id.ivCourseIcon)
        val teacher: TextView = itemView.findViewById(R.id.tvTeacherName)
        val check: ImageView = itemView.findViewById(R.id.ivCheck)

        fun bind(item: Course) {
            name.text = item.name
            description.text = item.description
            teacher.text = item.teacher
            Picasso.get()
                    .load(item.image_url)
                    .into(icon)
            if (item.selected) {
                check.visibility = VISIBLE
                if (item.percents == 100) {
                    Picasso.get()
                            .load(R.drawable.ic_check_circle)
                            .into(check)
                } else {
                    Picasso.get()
                            .load(R.drawable.ic_timer_clock)
                            .into(check)
                }
            } else {
                check.visibility = GONE
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Course>() {
            override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem::class == newItem::class
            }

            override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
                return oldItem == newItem
            }
        }
    }
}