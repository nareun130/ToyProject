package com.nareun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nareun.domain.Todo;

public interface TodoRepository  extends JpaRepository<Todo,Long>{
    
}
