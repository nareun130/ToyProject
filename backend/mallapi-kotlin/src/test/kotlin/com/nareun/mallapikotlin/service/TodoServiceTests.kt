package com.nareun.mallapikotlin.service

import com.nareun.mallapikotlin.dto.PageRequestDTO
import com.nareun.mallapikotlin.dto.PageResponseDTO
import com.nareun.mallapikotlin.dto.TodoDTO
import com.nareun.mallapikotlin.repository.TodoRepositoryTests
import lombok.extern.log4j.Log4j2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
@Log4j2
class TodoServiceTests {

    @Autowired
    private  lateinit var todoService: TodoService

    companion object{
        private val log = LoggerFactory.getLogger(TodoServiceTests::class.java)
    }

    @Test
    fun testRegister(){
        val todoDTO = TodoDTO(
            title = "서비스테스트",
            writer = "tester",
            dueDate = LocalDate.of(2024,4,8 )
        )
        val tno = todoService.register(todoDTO)
        log.info("TNO : $tno");
    }

    @Test
    fun testGet(){
        val tno = 10L

        val todoDTO : TodoDTO = todoService.get(tno)

        log.info(todoDTO.toString())

    }

    @Test
    fun testList(){
        val pageRequestDTO : PageRequestDTO = PageRequestDTO(
            page = 2,
            size = 10
        )
        val response : PageResponseDTO<TodoDTO> = todoService.list(pageRequestDTO)
        log.info(response.toString())
    }
}