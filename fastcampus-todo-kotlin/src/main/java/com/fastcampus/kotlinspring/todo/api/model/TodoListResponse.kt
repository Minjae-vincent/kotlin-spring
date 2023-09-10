package com.fastcampus.kotlinspring.todo.api.model

import com.fastcampus.kotlinspring.todo.domain.Todo
import com.fasterxml.jackson.annotation.JsonIgnore

data class TodoListResponse(
  val items: List<TodoResponse>,
) {
  val size: Int
    @JsonIgnore
    get() = items.size

  fun get(idx: Int) = items[idx]

  companion object{
    fun of (todoList: List<Todo>) =
      TodoListResponse(todoList.map(TodoResponse::of))
  }

}