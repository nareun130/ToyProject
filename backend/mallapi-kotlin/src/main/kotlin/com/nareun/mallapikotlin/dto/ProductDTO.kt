package com.nareun.mallapikotlin.dto

import org.springframework.web.multipart.MultipartFile

data class ProductDTO (
    var pno :Long=0,
    var pname : String="",
    var price: Int = 0,
    var pdesc : String = "",
    var deFlag : Boolean = false,
    //실제 파일 데이터
    var files: List<MultipartFile> ?= mutableListOf(),
    // 업로드된 파일 결과들
    var uploadFileNames:List<String> ?= mutableListOf()

){
    //ModelMapper를 위한 기본 생성자
    constructor(): this(0,"",0,"",false, mutableListOf(), mutableListOf())
}