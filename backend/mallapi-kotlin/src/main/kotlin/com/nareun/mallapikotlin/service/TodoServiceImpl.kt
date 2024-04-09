package com.nareun.mallapikotlin.service

import com.nareun.mallapikotlin.domain.Todo
import com.nareun.mallapikotlin.dto.PageRequestDTO
import com.nareun.mallapikotlin.dto.PageResponseDTO
import com.nareun.mallapikotlin.dto.TodoDTO
import com.nareun.mallapikotlin.repository.TodoRepository
import lombok.extern.log4j.Log4j2
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional // 직압이 정상적으로 처리 -> 커밋, 그렇지 못하면 rollback
@Log4j2
class TodoServiceImpl(
    private val modelMapper: ModelMapper,
    private val todoRepository: TodoRepository
) : TodoService {

    val log = LoggerFactory.getLogger(TodoServiceImpl::class.java)

    override fun register(todoDTO: TodoDTO): Long? {
        log.info("------------")

        val todo = modelMapper.map(todoDTO, Todo::class.java)
        val savedTodo = todoRepository.save(todo);

        return savedTodo.getTno()

    }

    override fun get(tno: Long): TodoDTO {
//        val result : Optional<Todo> = todoRepository.findById(tno)
//        val todo : Todo = result.orElseThrow();
//        val dto : TodoDTO= modelMapper.map(todo, TodoDTO::class.java)
        // 코틀린의 타입추론으로 변경
        val result = todoRepository.findById(tno)
        val todo = result.orElseThrow()
        val dto = modelMapper.map(todo, TodoDTO::class.java)

        return dto

    }

    override fun modify(todoDTO: TodoDTO) {
        val result = todoRepository.findById(todoDTO.tno ?: 0)// TodoDto가 null을 허용하면 TypeMismatch에러 발생
        val todo = result.orElseThrow()

        todo.changeTitle(todoDTO.title ?: "") // null일 경우 ""로 설정
        todo.changeDueDate(todoDTO.dueDate)
        todo.changeComplete(todoDTO.complete)

        todoRepository.save(todo)

    }

    override fun remove(tno: Long) {
        todoRepository.deleteById(tno)
    }

    override fun list(pageRequestDTO: PageRequestDTO): PageResponseDTO<TodoDTO> {
        val pageable: Pageable = PageRequest.of(
            pageRequestDTO.page - 1,
            pageRequestDTO.size,
            Sort.by("tno").descending()
        )

        val result = todoRepository.findAll(pageable)
        val dtoList = result.content.map { todo -> modelMapper.map(todo, TodoDTO::class.java) }

        val totalCount = result.totalElements

//        return PageResponseDTO.withAll(dtoList, pageRequestDTO, totalCount)

        return PageResponseDTO.withAll(
            dtoList = dtoList,
            pageRequestDTO = pageRequestDTO,
            totalCount = totalCount
        )
    }
}