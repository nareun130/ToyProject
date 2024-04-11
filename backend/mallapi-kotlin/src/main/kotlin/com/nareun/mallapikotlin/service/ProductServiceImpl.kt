package com.nareun.mallapikotlin.service

import com.nareun.mallapikotlin.domain.Product
import com.nareun.mallapikotlin.domain.ProductImage
import com.nareun.mallapikotlin.dto.PageRequestDTO
import com.nareun.mallapikotlin.dto.PageResponseDTO
import com.nareun.mallapikotlin.dto.ProductDTO
import com.nareun.mallapikotlin.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductServiceImpl : ProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    val log = LoggerFactory.getLogger(ProductServiceImpl::class.java)

    override fun getList(pageRequestDTO: PageRequestDTO): PageResponseDTO<ProductDTO> {
        log.info("getList..............")

        val pageable: Pageable = PageRequest.of(
            //페이지 시작 번호가 0 부터 시작하기 때문
            pageRequestDTO.page - 1,
            pageRequestDTO.size,
            Sort.by("pno").descending()
        )

        val result: Page<Array<Any>> = productRepository.selectList(pageable)

        val dtoList = result.map { arr ->
            val product = arr[0] as Product// 형변환
            val productImage = arr[1] as ProductImage


            ProductDTO(
                pno = product.pno,
                pname = product.pname,
                pdesc = product.pdesc,
                price = product.price,
                uploadFileNames = listOf(productImage.fileName)
            )
        }.content.toList()

        return PageResponseDTO.withAll(
            dtoList = dtoList,
            totalCount = result.totalElements,
            pageRequestDTO = pageRequestDTO
        )
    }

    override fun register(productDTO: ProductDTO): Long {
        val product : Product = dtoToEntity(productDTO)

        val result : Product = productRepository.save(product)

        return result.pno
    }

    private fun dtoToEntity(productDTO: ProductDTO): Product {
        val product = Product(
            pno = productDTO.pno,
            pname = productDTO.pname,
            pdesc = productDTO.pdesc,
            price = productDTO.price,
        )
        //업로드 처리가 끝난 파일들의 이름 리스트
        val uploadFileNames : List<String> ?= productDTO.uploadFileNames//? 처리 안할 시 오류

        uploadFileNames?.forEach { fileName -> product.addImageString(fileName) }

        return product
    }

    override fun get(pno: Long): ProductDTO {
        TODO("Not yet implemented")
    }

    override fun modify(productDTO: ProductDTO) {
        TODO("Not yet implemented")
    }

    override fun remove(pno: Long) {
        TODO("Not yet implemented")
    }

}