package org.nareun130.mallapi.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO<E> { //* 다른 타입의 DTO들을 이용할 수 있또록 제네릭 타입으로 선언
    
    private List<E> dtoList;

    private List<Integer> pageNumList;

    private PageRequestDTO pageRequestDTO;

    private boolean prev, next;

    private int totalCount, prevPage, nextPage, totalPage, current;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount) {

        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int)totalCount;

        // 페이지 목록의 마지막 data번호 10,20,30
        int end = (int)(Math.ceil(pageRequestDTO.getPage()/10.0)) * 10;

        // 페이지 시작 번호
        int start = end - 9;

        // 마지막 페이지 자리수 (1,2,3,4)
        int last = (int)(Math.ceil((totalCount/(double)pageRequestDTO.getSize())));
        
        //* end값 조정 -> data개수가 3인 경우 end값을 3으로 
        end = end > last ? last : end;

        this.prev = start > 1;

        this.next = totalCount > end * pageRequestDTO.getSize();

        this.pageNumList = IntStream.rangeClosed(start, end).boxed()
            .collect(Collectors.toList());

        if(prev) {
            this.prevPage = start - 1;
        }

        if(next) {
            this.nextPage = end + 1;
        }

        this.totalPage = this.pageNumList.size();

        this.current = pageRequestDTO.getPage();
    }
}
