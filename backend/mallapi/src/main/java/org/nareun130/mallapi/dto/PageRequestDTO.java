package org.nareun130.mallapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder //* 상속받은 필드도 builder에서 사용가능
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

}
