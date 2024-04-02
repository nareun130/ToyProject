package com.nareun.mallapikotlin.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "tbl_todo")
data class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val tno: Long?=null,

    var title: String,

    var writer: String,

    var complete: Boolean,

    var dueDate: LocalDate
) {
   fun changeTitle(title:String) {
       this.title = title
   }

    fun changeComplete(complete: Boolean) {
        this.complete = complete
    }

    fun changeDueDate(dueDate: LocalDate){
        this.dueDate = dueDate
    }
}