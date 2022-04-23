package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ItemTodoBinding

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private lateinit var binding: ItemTodoBinding

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size -1)
    }

    fun deleteDoneTasks() {
        todos.removeAll{ todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTask: TextView, isChecked: Boolean) {
        if (isChecked){
            tvTask.paintFlags = tvTask.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = todos[position]

        holder.itemView.apply {
            findViewById<TextView>(R.id.tvTasks).text = curTodo.title
            findViewById<CheckBox>(R.id.checkBox).isChecked = curTodo.isChecked
            toggleStrikeThrough(findViewById<TextView>(R.id.tvTasks),curTodo.isChecked )
            findViewById<CheckBox>(R.id.checkBox).setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(findViewById<TextView>(R.id.tvTasks), isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}