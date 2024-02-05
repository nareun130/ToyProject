package org.nareun130.mallapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.nareun130.mallapi.domain.Todo;
import org.nareun130.mallapi.dto.PageRequestDTO;
import org.nareun130.mallapi.dto.PageResponseDTO;
import org.nareun130.mallapi.dto.TodoDTO;
import org.nareun130.mallapi.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    /*
     * 등록
     */
    @Override
    public Long register(TodoDTO todoDTO) {
        log.info("-------------------------------TodoServiceImpl------------------------------------");

        Todo todo = modelMapper.map(todoDTO, Todo.class);

        Todo savedTodo = todoRepository.save(todo);
        
        return savedTodo.getTno();
    }

    /*
     * 조회
     */
    @Override
    public TodoDTO get(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        TodoDTO dto = modelMapper.map(todo, TodoDTO.class);
        
        return dto;
    }

    /*
     * 수정
     */
    @Override
    public void modify(TodoDTO todoDTO) {
        
        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());

        Todo todo = result.orElseThrow();

        todo.changeTitle(todoDTO.getTitle());
        todo.changeDueDate(todoDTO.getDueDate());
        todo.changeComplete(todoDTO.isComplete());

        todoRepository.save(todo);
    }

    /*
     * 삭제
     */
    @Override
    public void remove(Long tno) {
        
        todoRepository.deleteById(tno);
        
    }

    /*
     * 목록
     */
    @Override
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() -1, //! 주의 :  1페이지가 0
                pageRequestDTO.getSize(), 
                Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        List<TodoDTO> dtoList = result.getContent().stream()
            .map(todo -> modelMapper.map(todo,TodoDTO.class))
            .collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        PageResponseDTO<TodoDTO> responseDTO = 
            PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

        return responseDTO;
    }
    
}
