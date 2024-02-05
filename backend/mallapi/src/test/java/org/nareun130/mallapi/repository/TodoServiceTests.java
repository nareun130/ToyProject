package org.nareun130.mallapi.repository;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.nareun130.mallapi.dto.TodoDTO;
import org.nareun130.mallapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoServiceTests {
    
    @Autowired
    private TodoService todoService;

    /*
     * todo 등록 테스트
     */
    @Test
    public void testRegister() {

        TodoDTO todoDTO = TodoDTO.builder()
            .title("서비스 테스트")
            .writer("tester")
            .dueDate(LocalDate.of(2024, 02, 05))
            .build();
        
        Long tno = todoService.register(todoDTO);

        log.info("TNO : " + tno);
    }

    /*
     * todo 조회 테스트
     */
    @Test
    public void testGet() {

        Long tno = 10L;

        TodoDTO todoDTO = todoService.get(tno);

        log.info(todoDTO);
    }

    /*
     * todo 수정 테스트
     */
    @Test
    public void testModify() {
        
        TodoDTO todoDTO = TodoDTO.builder()
            .tno(10L)
            .title("서비스 수정 테스트")
            .writer("tester")
            .dueDate(LocalDate.of(2024, 02, 5))
            .build();
        todoService.modify(todoDTO);

        log.info(todoDTO);
    }

    /*
     *  todo 삭제 테스트
     */
    @Test
    public void testDelete() {

        Long tno = 10L;
        todoService.remove(tno);

        log.info(tno);
    }
}
