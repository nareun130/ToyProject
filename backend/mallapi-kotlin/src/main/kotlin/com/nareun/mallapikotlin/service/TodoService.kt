package com.nareun.mallapikotlin.service

import com.nareun.mallapikotlin.dto.TodoDTO
import org.springframework.data.domain.AbstractPageRequest

interface TodoService {
     fun register(todoDTO: TodoDTO) : Long?

     fun get(tno:Long) : TodoDTO

     fun modify(todoDTO: TodoDTO)

     fun remove(tno:Long)

//     fun list(pageRequest: AbstractPageRequest) : PageResponseDTO<TodoDTO>
}