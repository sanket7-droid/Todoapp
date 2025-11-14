package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM todo_table ORDER BY id DESC")
    fun getAllTodos(): LiveData<List<Todo>>
}
