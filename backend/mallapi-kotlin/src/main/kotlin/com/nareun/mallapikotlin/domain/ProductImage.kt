package com.nareun.mallapikotlin.domain

import jakarta.persistence.Embeddable

@Embeddable// 값 타입을 정의
class ProductImage (
    var fileName: String = "",
    var ord: Int = 0
){
    override fun toString(): String {
        return "Product Image : (fileName:${this.fileName}, ord:${this.ord})"
    }
}