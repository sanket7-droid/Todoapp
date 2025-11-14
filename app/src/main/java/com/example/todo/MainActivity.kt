package com.example.todo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.adapter.TodoAdapter
import com.example.todo.data.Todo
import com.example.todo.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: TodoViewModel by viewModels()
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputTask = findViewById<EditText>(R.id.inputTask)
        val addBtn = findViewById<Button>(R.id.addBtn)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = TodoAdapter(emptyList()) { todo ->
            viewModel.delete(todo)

        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.allTodos.observe(this) { list ->
            adapter.updateList(list)
        }

        addBtn.setOnClickListener {
            val title = inputTask.text.toString()
            if (title.isNotEmpty()) {
                viewModel.insert(Todo(title = title))
                inputTask.text.clear()
            }
        }
    }
}
