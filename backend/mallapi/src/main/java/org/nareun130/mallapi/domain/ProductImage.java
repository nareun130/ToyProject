package org.nareun130.mallapi.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable// *값 타입 객체를 나타낸다.
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {

    private String fileName;

    private int ord;

    //* 첫 번째 사진만 보여주기 위해
    public void setOrd(int ord) {
        this.ord = ord;
    }
}
