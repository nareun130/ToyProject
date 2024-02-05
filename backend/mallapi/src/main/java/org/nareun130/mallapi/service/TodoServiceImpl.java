package org.nareun130.mallapi.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.nareun130.mallapi.domain.Todo;
import org.nareun130.mallapi.dto.TodoDTO;
import org.nareun130.mallapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor//* 생성자 자동주입
public class TodoServiceImpl implements TodoService{

    //*생성자 자동주입을 위해 final로 선언
    private final ModelMapper modelMapper;

    private final TodoRepository todoRepository;

    @Override
    public Long register(TodoDTO todoDTO) {
        log.info("-------------------------------TodoServiceImpl------------------------------------");

        Todo todo = modelMapper.map(todoDTO, Todo.class);

        Todo savedTodo = todoRepository.save(todo);
        
        return savedTodo.getTno();
    }

    @Override
    public TodoDTO get(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        TodoDTO dto = modelMapper.map(todo, TodoDTO.class);
        
        return dto;
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        
        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());

        Todo todo = result.orElseThrow();

        todo.changeTitle(todoDTO.getTitle());
        todo.changeDueDate(todoDTO.getDueDate());
        todo.changeComplete(todoDTO.isComplete());

        todoRepository.save(todo);
    }

    @Override
    public void remove(Long tno) {
        
        todoRepository.deleteById(tno);
        
    }
    
}
