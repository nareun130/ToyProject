package com.nareun.mallapikotlin.dto

import kotlin.math.ceil

data class PageResponseDTO<E>(
    private val dtoList: List<E>,
    private val pageRequestDTO: PageRequestDTO,
    private val totalCount: Long
) {
    // init 하기 전에는 컴파일 오류가 나타남. -> 초기화를 위해
    private var pageNumList: List<Int>
    private var prev: Boolean
    private var next: Boolean
    private var prevPage: Int
    private var nextPage: Int
    private var totalPage: Int
    private var current: Int

    //클래스 초기화 블록
    init {
        //현재 페이지를 기준으로 계산된 페이지 그룹의 마지막 페이지 번호
        var end = (ceil(pageRequestDTO.page / 10.0) * 10).toInt()
        //페이지 그룹의 시작 페이지 번호
        val start = end - 9
        //전체 데이터를 기준으로 계산된 마지막 페이지 번호
        val last = (ceil(totalCount / pageRequestDTO.size.toDouble())).toInt()
        //마지막 페이지 번호가 end보다 작으면 last 그렇지 않으면 10
        end = if (end > last) last else end
        //이전 페이지 그룹 존재 여부 (page그룹 : 1~10 -> 1. 11~20 -> 2)
        prev = start > 1
        // 다음 페이지 그룹 존재 여부
        next = totalCount > end * pageRequestDTO.size
        //현재 페이지에 속한 페이지 번호의 리스트
        pageNumList = (start..end).toList()
        //이전과 다음 페이지 그룹으로 이동할 때의 페이지 번호
        prevPage = if (prev) start - 1 else 0
        nextPage = if (next) end + 1 else 0
        // 현재 페이지 그룹에 속한 총 페이지 수
        totalPage = pageNumList.size
        //현재 페이지 번호
        current = pageRequestDTO.page
    }

    //정적 메소드 정의
    companion object {
        fun withAll(dtoList: List<TodoDTO>, pageRequestDTO: PageRequestDTO, totalCount: Long)
        = PageResponseDTO(dtoList, pageRequestDTO, totalCount)
    }
}