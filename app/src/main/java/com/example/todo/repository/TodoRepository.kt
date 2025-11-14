package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.data.Todo
import com.example.todo.data.TodoDao

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }
}
