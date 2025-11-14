package com.example.todo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.todo.data.Todo
import com.example.todo.data.TodoDatabase
import com.example.todo.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository
    val allTodos: LiveData<List<Todo>>

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        allTodos = repository.allTodos
    }

    fun insert(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(todo)
    }

    fun delete(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(todo)
    }
}
