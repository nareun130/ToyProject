package com.zerock.mallapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zerock.mallapi.domain.CartItem;
import com.zerock.mallapi.dto.CartItemListDTO;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // 사용자의 이메일 -> 모든 장바구니 아이템 조희
    // * 로그인 시 모든 장바구니 아이템 조희
    @Query("select " +
            " new com.zerock.mallapi.dto.CartItemListDTO(ci.cino,  ci.qty,  p.pno, p.pname, p.price , pi.fileName )  " +
            " from " +
            "   CartItem ci inner join Cart mc on ci.cart = mc " +
            "   left join Product p on ci.product = p " +
            "   left join p.imageList pi" +
            " where " +
            "   mc.owner.email = :email and pi.ord = 0 " +
            " order by ci desc ")
    public List<CartItemListDTO> getItemsOfCartDTOByEmail(@Param("email") String email);

    // 사용자 이메일 + 상품 번호 -> 장바구니 아이템
    // * 장바구니에 추가 시 기존에 속한지 확인
    @Query("select" +
            " ci " +
            " from " +
            "   CartItem ci inner join Cart c on ci.cart = c " +
            " where " +
            "   c.owner.email = :email and ci.product.pno = :pno")
    public CartItem getItemOfPno(@Param("email") String email, @Param("pno") Long pno);

    // 아이템이 속한 장바구니 번호
    // * 해당 아이템 삭제 후 해당 아이템이 속한 장바구니의 모든 아이템을 알아내기위해
    @Query("select " +
            " c.cno " +
            "from " +
            "   Cart c inner join CartItem ci on ci.cart = c " +
            " where " +
            "    ci.cino = :cino")
    public Long getCartFromItem(@Param("cino") Long cino);

    // 장바구니 번호 -> 모든 아이템 조회
    // * 해당 아이템 삭제 후 해당 아이템이 속한 장바구니의 모든 아이템을 알아내기위해

    @Query("select new com.zerock.mallapi.dto.CartItemListDTO(ci.cino,  ci.qty,  p.pno, p.pname, p.price , pi.fileName )  "
            +
            " from " +
            "   CartItem ci inner join Cart mc on ci.cart = mc " +
            "   left join Product p on ci.product = p " +
            "   left join p.imageList pi" +
            " where " +
            "  mc.cno = :cno and pi.ord = 0 " +
            " order by ci desc ")
    public List<CartItemListDTO> getItemsOfCartDTOByCart(@Param("cno") Long cno);

}