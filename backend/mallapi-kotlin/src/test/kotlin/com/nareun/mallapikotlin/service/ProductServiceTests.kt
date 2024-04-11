package com.nareun.mallapikotlin.service

import com.nareun.mallapikotlin.dto.PageRequestDTO
import com.nareun.mallapikotlin.dto.PageResponseDTO
import com.nareun.mallapikotlin.dto.ProductDTO
import lombok.extern.log4j.Log4j2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
@Log4j2
class ProductServiceTests {
    @Autowired
    private lateinit var productService : ProductService

    private val log = LoggerFactory.getLogger(ProductServiceTests::class.java)
    @Test
    fun testList(){
        val pageRequestDTO : PageRequestDTO = PageRequestDTO()

        val result : PageResponseDTO<ProductDTO> = productService.getList(pageRequestDTO)

        result.dtoList.forEach { dto ->  log.info(dto.toString())}

    }

    @Test
    fun testRegister(){
        val productDTO : ProductDTO = ProductDTO(
            pname = "새로운 상품",
            pdesc = "신규 추가 상품",
            price = 1000
        )
        productDTO.uploadFileNames = listOf(
            "${UUID.randomUUID()}_Test1.jpg",
            "${UUID.randomUUID()}_Test2.jpg",
        )

        productService.register(productDTO)
    }


}