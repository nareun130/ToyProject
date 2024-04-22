package com.zerock.mallapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString(exclude = "cart")
/*
 * Product 엔티티와 Cart 엔티티를 동시에 참조할 때 조회 성능을 향상시킵니다. 예를 들어, 특정 상품이 특정 쇼핑 카트에 포함되어
 * 있는지 빠르게 확인할 수 있게 해주며, 특정 카트에서 특정 상품을 조회하는 쿼리가 이 인덱스에 의해 빠르게 처리됩니다.
 * 
 */
@Table(name = "tbl_cart_item", indexes = {
        @Index(columnList = "cart_cno", name = "idx_cartitem_cart"),
        @Index(columnList = "product_pno, cart_cno", name = "idx_cartitem_pno_cart")
})
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cino;

    @ManyToOne
    @JoinColumn(name = "product_pno")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_cno")
    private Cart cart;

    private int qty;

    public void changeQty(int qty) {
        this.qty = qty;
    }

}