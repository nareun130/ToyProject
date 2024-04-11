package com.nareun.mallapikotlin.domain

import jakarta.persistence.*

@Entity
@Table(name = "tbl_product")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var pno: Long = 0,

    var pname: String = "",

    var price: Int = 0,

    var pdesc: String = "",

    var delFlag: Boolean = false,

    @ElementCollection//컬렉션 객체임을 JPA가 알수 있도록, 엔티티가 x, 값타입 임베디드 타입에 대한 태이블 생성 & 1대다 관계로 다룸
    var imageList: MutableList<ProductImage> = mutableListOf()//ProductImage가 값 타입 객체이므로
) {

    fun changeDel(delFlag: Boolean) {
        this.delFlag = delFlag
    }

    fun changePrice(price: Int) {
        this.price = price
    }

    fun changeName(name: String) {
        this.pname = name
    }

    fun addImage(image: ProductImage) {
        image.ord = this.imageList.size
        imageList.add(image)
    }

    fun addImageString(fileName: String) {
        val productImage = ProductImage(fileName, this.imageList.size)
        addImage(productImage)
    }

    fun changeDesc(desc: String) {
        this.pdesc = desc
    }

    fun clearList() {
        this.imageList.clear()
    }

    override fun toString(): String {
        return "pno : ${this.pno} ,pname:${this.pname} ,price:${this.price} ,pdesc:${this.pdesc} ,delFlag:${this.delFlag}"
    }
}