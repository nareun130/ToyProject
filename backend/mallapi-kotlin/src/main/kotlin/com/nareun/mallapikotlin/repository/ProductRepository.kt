package com.nareun.mallapikotlin.repository

import com.nareun.mallapikotlin.domain.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface ProductRepository : JpaRepository<Product,Long> {

    //@EntityGraph를 이용하여 해당 속성을 조인처리
    @EntityGraph(attributePaths = ["imageList"])
    @Query("select p from Product p where p.pno = :pno")
    fun selectOne(@Param("pno") pno: Long) : Optional<Product>

    @Modifying// DML 사용 시 선언
    //@Modifying을 변경이 일어나는 쿼리와 함께 사용해야 JPA에서 변경 감지와 관련된 처리를 생략하고 더 효율적인 실행이 가능하다.
    @Query("update Product p set p.delFlag = :flag where p.pno = :pno")
    fun updateToDelete(@Param("pno") pno: Long, @Param("flag") flag:Boolean)

    //상품의 대표 이미지만 가져오기 위해 join 처리
    @Query("select p, pi from Product p left join p.imageList pi where pi.ord = 0 and p.delFlag = false")
    fun selectList(pageable: Pageable) : Page<Array<Any>> // java : Page<Object[]>

}