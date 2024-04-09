package com.nareun.mallapikotlin.dto

import kotlin.math.ceil

data class PageResponseDTO<E>(
    val dtoList: List<E>,
    val pageNumList: List<Int>,
    val pageRequestDTO: PageRequestDTO,
    val prev:Boolean,
    val next:Boolean,
    val totalCount : Int,
    val prevPage:Int,
    val nextPage:Int,
    val totalPage:Int,
    val current : Int
){
    companion object{
        fun <E> withAll(dtoList: List<E>, pageRequestDTO: PageRequestDTO,totalCount: Long) : PageResponseDTO<E>{
            val end = (Math.ceil(pageRequestDTO.page/10.0)*10).toInt()
            val start = end - 9
            val last = (Math.ceil(totalCount / pageRequestDTO.size.toDouble())).toInt()
            val realEnd = if (end > last) last else end
            val prev = start > 1
            val next = totalCount > realEnd * pageRequestDTO.size

            return PageResponseDTO(
                dtoList = dtoList,
                pageRequestDTO = pageRequestDTO,
                totalCount = totalCount.toInt(),
                prev = prev,
                next = next,
                pageNumList = IntRange(start, realEnd).toList(),
                prevPage = if (prev) start - 1 else 0,
                nextPage = if (next) realEnd + 1 else 0,
                totalPage = last,
                current = pageRequestDTO.page
            )
        }
    }
}