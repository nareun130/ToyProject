package org.nareun130.mallapi.repository;

import org.nareun130.mallapi.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
