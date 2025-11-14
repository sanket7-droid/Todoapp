package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.Todo

class TodoAdapter(
    private var todos: List<Todo>,
    private val onDelete: (Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.todoTitle)
        val checkBox: CheckBox = view.findViewById(R.id.todoCheck)
        val deleteIcon: ImageView = view.findViewById(R.id.deleteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.title.text = todo.title
        holder.checkBox.isChecked = todo.isDone

        holder.deleteIcon.setOnClickListener {
            onDelete(todo)
        }
    }

    override fun getItemCount() = todos.size

    fun updateList(newList: List<Todo>) {
        todos = newList
        notifyDataSetChanged()
    }
}
