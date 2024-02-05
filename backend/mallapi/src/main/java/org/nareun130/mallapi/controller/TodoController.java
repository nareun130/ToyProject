package org.nareun130.mallapi.controller;

import org.nareun130.mallapi.dto.PageRequestDTO;
import org.nareun130.mallapi.dto.PageResponseDTO;
import org.nareun130.mallapi.dto.TodoDTO;
import org.nareun130.mallapi.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/todo")
public class TodoController {
    
    private final TodoService service;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable(name = "tno") Long tno) {
        
        return service.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list (PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        return service.list(pageRequestDTO);
    }
    
    
}
