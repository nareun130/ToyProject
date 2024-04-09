package com.nareun.mallapikotlin.domain

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "tbl_todo")
data class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val tno: Long?= null, // null을 허용
//    val tno :Long = 0,
    private  val tno: Long? = null,

    private var title: String,

    private var writer: String,

    private var complete: Boolean,

    private var dueDate: LocalDate


) {
    //기본 생성자를 생성 해주지 않으면 modelMapper에서 만들지를 못하는 거 같다.
    constructor() : this(null,"","",false, LocalDate.now())
    fun getTno():Long{
        return  this.tno?:0
    }
    fun changeTitle(title: String) {
        this.title = title
    }

    fun changeComplete(complete: Boolean) {
        this.complete = complete
    }

    fun changeDueDate(dueDate: LocalDate) {
        this.dueDate = dueDate
    }
}