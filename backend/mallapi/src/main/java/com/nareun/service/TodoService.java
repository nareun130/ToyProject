package com.nareun.service;

import com.nareun.dto.PageRequestDTO;
import com.nareun.dto.PageResponseDTO;
import com.nareun.dto.TodoDTO;

public interface TodoService {
    Long register(TodoDTO todoDTO);

    TodoDTO get(Long tno);

    void modify(TodoDTO todoDTO);

    void remove(Long tno);

    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
