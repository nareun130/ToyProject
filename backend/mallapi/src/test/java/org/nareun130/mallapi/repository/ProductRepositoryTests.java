package org.nareun130.mallapi.repository;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.nareun130.mallapi.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductRepositoryTests {
    
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testInsert() {

        for(int i = 0;  i < 10; i++) {

            Product product = Product.builder()    
                .pname("상품" + i)
                .price(100*i) 
                .pdesc("상품설명 " + i)
                .build();

            //2개의 이미지 파일을 추가
            product.addImageString(UUID.randomUUID().toString()+"_"+"IMAGE1.jpg");
            product.addImageString(UUID.randomUUID().toString()+"_"+"IMAGE2.jpg");

            productRepository.save(product);

            log.info("-------------------------------");
        }

        
    }

    @Transactional //* db를 2번 타야해서 Transactional 처리
    @Test
    public void testRead() {

        Long pno = 1L;

        Optional<Product> result = productRepository.findById(pno);

        Product product = result.orElseThrow();

        log.info(product);
        log.info(product.getImageList());

    }
}
