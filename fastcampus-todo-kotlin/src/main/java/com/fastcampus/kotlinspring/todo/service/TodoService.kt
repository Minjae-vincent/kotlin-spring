package com.fastcampus.kotlinspring.todo.service

import com.fastcampus.kotlinspring.todo.api.model.TodoRequest
import com.fastcampus.kotlinspring.todo.domain.Todo
import com.fastcampus.kotlinspring.todo.domain.TodoRepository
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class TodoService (
  private val todoRepository: TodoRepository
){
  @Transactional(readOnly = true)
  fun findAll() : List<Todo> =
    todoRepository.findAll(by(Direction.DESC, "id"))

  @Transactional(readOnly = true)
  fun findById(id: Long) : Todo =
    todoRepository.findByIdOrNull(id)
      // null인 경우, 아래 로직을 따름
      ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

  @Transactional
  fun create(request: TodoRequest?) : Todo {
    // 아래 checkNotNull을 통해 request라는 객체는 null이 아닌 것이 확정됨
    checkNotNull(request) { "TodoRequest is null" }

    // 때문에 아래 Todo 객체를 생성할 때 ?를 통해 nullable임을 보장하지 않아도 됨! 어차피 null이 아니니깐
    val todo = Todo(
      title = request.title,
      description = request.description,
      createdAt = request.done
    )

    return todoRepository.save(todo)
  }

  @Transactional
  fun update(id: Long, request: TodoRequest?): Todo{
    checkNotNull(request) { "TodoRequest is null" }

    return findById(id).let {
      it.update(request.title, request.description, request.done)
      todoRepository.save(it)
    }

  }

  fun delete(id: Long) = todoRepository.deleteById(id)
}