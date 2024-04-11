package com.nareun.mallapikotlin.domain

import jakarta.persistence.*
import java.time.LocalDate
import java.util.Objects

@Entity
@Table(name = "tbl_todo")
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val tno: Long?= null, // null을 허용
//    val tno :Long = 0,
    private  val tno: Long = 0,

    private var title: String,

    private var writer: String,

    private var complete: Boolean,

    private var dueDate: LocalDate


) {
    //기본 생성자를 생성 해주지 않으면 modelMapper에서 만들지를 못하는 거 같다.
    constructor() : this(0,"","",false, LocalDate.now())
    fun getTno():Long{
        return  this.tno
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
    override fun toString():String{
        return "tno : ${this.tno}, title:${this.title}, writer:${this.writer}, complete:${this.complete}, dueDate:${this.dueDate}"
    }

    override fun equals(other: Any?): Boolean {
        if(other ==null){
            return false
        }
        if(this::class !=other::class){
            return false
        }
        return this.tno == (other as Todo).tno
    }

    override fun hashCode() = Objects.hashCode(tno)

}