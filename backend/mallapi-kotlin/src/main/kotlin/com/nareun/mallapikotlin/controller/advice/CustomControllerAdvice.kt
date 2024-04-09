package com.nareun.mallapikotlin.controller.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomControllerAdvice {

    @ExceptionHandler(NoSuchElementException::class)
    protected fun notExist(e: NoSuchElementException): ResponseEntity<*> {
        val msg = e.message ?: "No message available"
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("msg" to msg))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleIllegalArgument(e: MethodArgumentNotValidException): ResponseEntity<*> {
        val msg = e.message ?: "No message availabel"
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(mapOf("msg" to msg))
    }
}