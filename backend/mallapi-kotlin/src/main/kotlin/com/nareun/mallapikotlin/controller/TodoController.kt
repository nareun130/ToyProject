package com.nareun.mallapikotlin.controller

import com.nareun.mallapikotlin.dto.PageRequestDTO
import com.nareun.mallapikotlin.dto.PageResponseDTO
import com.nareun.mallapikotlin.dto.TodoDTO
import com.nareun.mallapikotlin.service.TodoService
import lombok.extern.log4j.Log4j2
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Log4j2
@RequestMapping("/api/todo")
class TodoController (private val service: TodoService){

    private val log = LoggerFactory.getLogger(TodoController::class.java)
//    @Autowired
//    private lateinit var todoService: TodoService

    @GetMapping("/{tno}")
    fun get(@PathVariable("tno") tno:Long): TodoDTO = service.get(tno)

    @GetMapping("/list")
    fun list(pageRequestDTO: PageRequestDTO) : PageResponseDTO<TodoDTO>{
        log.info(pageRequestDTO.toString())
        return service.list(pageRequestDTO)
    }
}