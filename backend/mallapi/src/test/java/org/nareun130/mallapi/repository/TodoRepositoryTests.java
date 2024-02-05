package org.nareun130.mallapi.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.nareun130.mallapi.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {
        
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1() {
        log.info("-----------------------");
        log.info(todoRepository);
    }

    /*
     * todo 생성
     */
    @Test
    public void testInsert() {

        for(int i = 1; i<=100; i++){
            Todo todo = Todo.builder()
            .title("Title..." + i)
            .dueDate(LocalDate.of(2024,02,05))
            .writer("nareun130")
            .build();

            todoRepository.save(todo);
        }
    }

    /*
     * todo 조회
     */
    @Test
    public void testRead() {

        Long tno = 33L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        log.info(todo);
        
    }

    /*
     * todo 수정
     */
    @Test
    public void testModify() {

        Long tno = 33L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();
        todo.changeTitle("Modified33...");
        todo.changeComplete(true);
        todo.changeDueDate(LocalDate.of(2024, 02, 05));

        todoRepository.save(todo);                                  
    }

    /*
     * todo 삭제
     */
    @Test
    public void testDelete() {

        Long tno = 33L;

        todoRepository.deleteById(tno);
    }

    /*
     * 페이징
     */
    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result.getTotalElements());

        result.getContent().stream().forEach(todo -> log.info(todo));
    }
}
