package com.nareun.mallapikotlin.controller

import com.nareun.mallapikotlin.dto.PageRequestDTO
import com.nareun.mallapikotlin.dto.PageResponseDTO
import com.nareun.mallapikotlin.dto.ProductDTO
import com.nareun.mallapikotlin.service.ProductService
import com.nareun.mallapikotlin.util.CustomFileUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productService: ProductService,
    private val fileUtil: CustomFileUtil
) {

    val log = LoggerFactory.getLogger(ProductController::class.java)

    @GetMapping("/list")
    fun list(pageRequestDTO: PageRequestDTO):PageResponseDTO<ProductDTO>{

        log.info("list.........$pageRequestDTO")

        return productService.getList(pageRequestDTO)
    }

    @PostMapping("/")
    fun register(productDTO: ProductDTO): Map<String, String> {
        log.info("register: $productDTO")

        val files: List<MultipartFile>? = productDTO.files

        val uploadFileNames = fileUtil.saveFiles(files)

        productDTO.uploadFileNames = uploadFileNames

        log.info(uploadFileNames.toString())

        return mutableMapOf("RESULT" to "SUCCESS")
    }

    @GetMapping("/view/{fileName}")
    fun viewFileGet(@PathVariable("fileName") fileName: String): ResponseEntity<Resource> {
        return fileUtil.getFile(fileName)
    }
}