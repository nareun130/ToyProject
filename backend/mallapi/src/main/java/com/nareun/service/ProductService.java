package com.nareun.service;

import org.springframework.transaction.annotation.Transactional;

import com.nareun.dto.PageRequestDTO;
import com.nareun.dto.PageResponseDTO;
import com.nareun.dto.ProductDTO;

@Transactional
public interface ProductService {

  PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO); 

  Long register(ProductDTO productDTO);

  ProductDTO get(Long pno);

  void modify(ProductDTO productDTO);

  void remove(Long pno);

}