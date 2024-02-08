package org.nareun130.mallapi.service;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.nareun130.mallapi.dto.PageRequestDTO;
import org.nareun130.mallapi.dto.PageResponseDTO;
import org.nareun130.mallapi.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductServiceTests {
    
    @Autowired
    ProductService productService;

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<ProductDTO> result = productService.getList(pageRequestDTO);

        result.getDtoList().forEach(dto -> log.info(dto));
    }

    @Test
    public void testRegister() {

        ProductDTO productDTO = ProductDTO.builder()    
            .pname("새로운 상품")
            .pdesc("신규 추가 상품")
            .price(1000)
            .build();

        //! uuid 필요!
        productDTO.setUploadFileNames(
            List.of(
                UUID.randomUUID()+"_" + "TEST1.jpg",
                UUID.randomUUID()+"_" + "TEST2.jpg"));
        
        productService.register(productDTO);
    }
    

    
}
