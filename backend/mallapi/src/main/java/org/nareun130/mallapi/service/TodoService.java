package org.nareun130.mallapi.service;

import org.nareun130.mallapi.dto.PageRequestDTO;
import org.nareun130.mallapi.dto.PageResponseDTO;
import org.nareun130.mallapi.dto.TodoDTO;

public interface TodoService {
    Long register(TodoDTO todoDTO);      

    TodoDTO get(Long tno);

    void modify(TodoDTO todoDTO);

    void remove(Long tno);

    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
} 
