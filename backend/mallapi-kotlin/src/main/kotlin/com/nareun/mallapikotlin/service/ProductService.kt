package com.nareun.mallapikotlin.service

import com.nareun.mallapikotlin.dto.PageRequestDTO
import com.nareun.mallapikotlin.dto.PageResponseDTO
import com.nareun.mallapikotlin.dto.ProductDTO
import org.springframework.transaction.annotation.Transactional

@Transactional
interface ProductService {
    fun getList(pageRequestDTO: PageRequestDTO) : PageResponseDTO<ProductDTO>

    fun register(productDTO: ProductDTO) : Long

    fun get(pno:Long) : ProductDTO

    fun modify(productDTO: ProductDTO)

    fun remove(pno: Long)
}