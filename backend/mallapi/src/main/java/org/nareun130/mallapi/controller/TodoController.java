package org.nareun130.mallapi.controller;

import java.util.Map;

import org.nareun130.mallapi.dto.PageRequestDTO;
import org.nareun130.mallapi.dto.PageResponseDTO;
import org.nareun130.mallapi.dto.TodoDTO;
import org.nareun130.mallapi.service.TodoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/todo")
public class TodoController {
    
    private final TodoService service;

    /*
     * 등록
     */
    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDTO todoDTO) {

        log.info(("TodoDTO : " + todoDTO));

        Long tno = service.register(todoDTO);

        return Map.of("TNO", tno);
    }
    

    /*
     * 조회
     */
    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable(name = "tno") Long tno) {
        
        return service.get(tno);
    }

    /*
     * 목록
     */
    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list (PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        return service.list(pageRequestDTO);
    }

    /*
     * 수정
     */
    @PutMapping("/{tno}")
    public Map<String, String> modify(
        @PathVariable (name = "tno") Long tno,
        @RequestBody TodoDTO todoDTO) {
            
            todoDTO.setTno(tno);

            log.info(("modify:" + todoDTO));

            service.modify(todoDTO);

            return Map.of("RESULT", "SUCCESS");
        }
    
    /*
     * 삭제
     */
    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable(name = "tno") Long tno ) {

        log.info("Remove : " + tno);

        service.remove(tno);

        return Map.of("RESULT", "SUCCESS");
    }
    
}
