package org.nareun130.mallapi.repository;

import org.nareun130.mallapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Long>{
    
}
