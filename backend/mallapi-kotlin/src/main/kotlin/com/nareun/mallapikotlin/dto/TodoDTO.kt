package com.nareun.mallapikotlin.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class TodoDTO (
    var tno:Long=0, // null 허용 O, 기본적으로 코틀린은 null허용 x
    var title: String?=null,
    var writer: String?=null,
    var complete:Boolean = false,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    var dueDate: LocalDate
)
{
    //기본 생성자를 만들어 주지 않으면 ModelMapper에서 에러가 나타남.
//    constructor() : this(0,"","",false,LocalDate.now())
    constructor() : this(0,null,null,false, LocalDate.now())
}