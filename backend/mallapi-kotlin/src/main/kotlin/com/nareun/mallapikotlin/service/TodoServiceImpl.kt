package com.nareun.mallapikotlin.service

import com.nareun.mallapikotlin.dto.TodoDTO
import lombok.extern.log4j.Log4j2
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional // 직압이 정상적으로 처리 -> 커밋, 그렇지 못하면 rollback
@Log4j2
class TodoServiceImpl : TodoService{

    val log = LoggerFactory.getLogger(TodoServiceImpl::class.java)
    override fun register(todoDTO: TodoDTO) : Long? {
        log.info("------------")
        return null

    }

    override fun get(tno: Long): TodoDTO {
        TODO("Not yet implemented")
    }

    override fun modify(todoDTO: TodoDTO) {
        TODO("Not yet implemented")
    }

    override fun remove(tno: Long) {
        TODO("Not yet implemented")
    }

}