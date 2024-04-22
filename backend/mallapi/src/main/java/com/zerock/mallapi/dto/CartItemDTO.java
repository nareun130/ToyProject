package com.zerock.mallapi.dto;

import lombok.Data;

@Data
public class CartItemDTO { ///* React로 전달되는 DTO

    private String email;

    private Long pno;

    private int qty;

    private Long cino;
}
