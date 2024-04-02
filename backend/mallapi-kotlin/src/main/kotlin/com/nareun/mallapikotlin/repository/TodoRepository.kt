package com.nareun.mallapikotlin.repository

import com.nareun.mallapikotlin.domain.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo,Long> {

}