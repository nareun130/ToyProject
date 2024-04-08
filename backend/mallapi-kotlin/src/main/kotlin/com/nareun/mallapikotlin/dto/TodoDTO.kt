package com.nareun.mallapikotlin.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class TodoDTO (
    var tno:Long?=null, // null 허용 x
    var title: String?=null,
    var writer: String?=null,
    var complete:Boolean = false,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    var dueDate: LocalDate
)
{
}