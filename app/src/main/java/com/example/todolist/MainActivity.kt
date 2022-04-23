package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ItemTodoBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoAdapter = TodoAdapter(mutableListOf())

        val rvTodoItems = binding.rvTodoItems
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        binding.btnAddTask.setOnClickListener {
            val todoTitle = binding.edtNewTask.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.edtNewTask.text.clear()
            }
        }

        binding.btnDeleteDoneTasks.setOnClickListener {
            todoAdapter.deleteDoneTasks()
        }
    }

}