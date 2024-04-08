package com.nareun.mallapikotlin.service

import com.nareun.mallapikotlin.dto.PageRequestDTO
import com.nareun.mallapikotlin.dto.PageResponseDTO
import com.nareun.mallapikotlin.dto.TodoDTO

interface TodoService {
     fun register(todoDTO: TodoDTO) : Long?

     fun get(tno:Long) : TodoDTO

     fun modify(todoDTO: TodoDTO)

     fun remove(tno:Long)

     fun list(pageRequestDTO: PageRequestDTO): PageResponseDTO<TodoDTO>

}