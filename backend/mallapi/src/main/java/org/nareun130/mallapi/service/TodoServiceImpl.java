package org.nareun130.mallapi.service;

import org.nareun130.mallapi.dto.TodoDTO;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class TodoServiceImpl implements TodoService{

    @Override
    public Long register(TodoDTO todoDTO) {
        log.info("-----------");

        return null;
    }
    
}
