package org.nareun130.mallapi.repository;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.nareun130.mallapi.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
