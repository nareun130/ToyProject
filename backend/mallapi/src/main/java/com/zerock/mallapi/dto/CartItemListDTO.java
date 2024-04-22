package com.zerock.mallapi.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
public class CartItemListDTO { // * 컨트롤러로 전달되는 DTO

    private Long cino;

    private int qty;

    private Long pno;

    private String pname;

    private int price;

    private String imageFile;

    public CartItemListDTO(Long cino, int qty, Long pno, String pname, int price, String imageFile) {
        this.cino = cino;
        this.qty = qty;
        this.pno = pno;
        this.pname = pname;
        this.price = price;
        this.imageFile = imageFile;
    }

}
