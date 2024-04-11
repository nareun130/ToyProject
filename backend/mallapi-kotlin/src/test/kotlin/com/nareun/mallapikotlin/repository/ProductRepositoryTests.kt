package com.nareun.mallapikotlin.repository

import com.nareun.mallapikotlin.domain.Product
import lombok.extern.log4j.Log4j2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Commit
import org.springframework.transaction.annotation.Transactional
import java.util.*


@SpringBootTest
@Log4j2
class ProductRepositoryTests {

    @Autowired
    lateinit var productRepository: ProductRepository

    val log = LoggerFactory.getLogger(ProductRepositoryTests::class.java)
    @Test
    fun testInsert() {
        repeat(10) { i ->
            val product = Product(
                pname = "상품$i",
                price = 100 * i,
                pdesc = "상품설명 $i"
            ).apply {
                //둘 다 사용 가능
                addImageString(UUID.randomUUID().toString()+"_"+"IMAGE1.jpg")
                addImageString("${UUID.randomUUID()}_IMAGE2.jpg")
            }
            productRepository.save(product)
            log.info("------------------")

        }
    }

    @Transactional //상품 정보를 가져오고 상품 이미지 리스트를 가져오므로 2번 쿼리를 타기 때문 -> Transactional
    @Test
    fun testRead() {
        val pno = 1L

        val product = productRepository.findById(pno).orElseThrow()

        log.info(product.toString())
        log.info(product.imageList.toString())
    }

    @Test
    fun testRead2(){
        val pno : Long = 1L
        val result : Optional<Product> = productRepository.selectOne(pno);

        val product : Product = result.orElseThrow()

        log.info(product.toString())
        log.info(product.imageList.toString())
    }

    @Commit// @Transactional이 기본적으로 롤백 처리를 하도록 설계되어있음.
    // -> 테스트 코드 실행 성공 후 업데이트 된 결과를 DB에서 확인하기 위해 @Commit사용
    @Transactional
    @Test
    fun testDelete(){
        val pno:Long = 2L;

        productRepository.updateToDelete(pno,true)
    }

    @Test
    fun testUpdate(){
        val pno = 10L

        val product :Product = productRepository.selectOne(pno).get()

        product.changeName("10번 상품")
        product.changeDesc("10번 상품 설명입니다.")
        product.changePrice(5000)

        //첨부파일 수정
        product.clearList()

        product.addImageString("${UUID.randomUUID()}_NEWIMAGE1.jpg")
        product.addImageString("${UUID.randomUUID()}_NEWIMAGE2.jpg")
        product.addImageString("${UUID.randomUUID()}_NEWIMAGE3.jpg")

        productRepository.save(product)
    }
}