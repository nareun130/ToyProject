package com.nareun.mallapikotlin.repository

import com.nareun.mallapikotlin.domain.Todo
import lombok.extern.log4j.Log4j2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import java.time.LocalDate
import java.util.Optional

@SpringBootTest
@Log4j2
class TodoRepositoryTests {

    @Autowired
    private lateinit var todoRepository: TodoRepository

//val log = LoggerFactory.getLogger(TodoRepositoryTests::class.java)
    companion object{
        val log = LoggerFactory.getLogger(TodoRepositoryTests::class.java)
    }

    @Test
    fun testInsert(){
        for(i in 1..100){
            val todo = Todo(
                title = "Title..."+i,
                dueDate = LocalDate.of(2024,4,2),
                writer = "user00",
                complete = false
            )
            todoRepository.save(todo)
        }
    }

    @Test
    fun testRead(){

        val tno = 33L
        val result: Optional<Todo> = todoRepository.findById(tno)
        val todo: Todo = result.orElseThrow()
        log.info(todo.toString())
    }

    @Test
    fun testModify(){

        val tno:Long = 33L
        val result : Optional<Todo> = todoRepository.findById(tno)
        val todo : Todo = result.orElseThrow()
        todo.changeTitle("Modified33...")
        todo.changeComplete(true)
        todo.changeDueDate(LocalDate.of(2024,4,2))
        todoRepository.save(todo)
    }


    @Test
    fun testDelete(){
        val tno : Long = 1L

        todoRepository.deleteById(tno)
    }

    @Test
    fun testPaging(){
        val pageable:Pageable = PageRequest.of(0,10, Sort.by("tno").descending())
        val result : Page<Todo> = todoRepository.findAll(pageable)
//      data총 개수
        log.info(result.totalElements.toString())
        result.content.forEach{todo -> log.info(todo.toString())}
    }
}