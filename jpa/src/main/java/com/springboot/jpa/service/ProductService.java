package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;

// 예제 6.20
// ProductService 인터페이스 설계
// DAO에서 구현한 기능을 서비스 인터페이스에서 호출해 결괏값을 가져오는 작업을 수행하도록 설계했다.
// 서비스에서는 클라이언트가 요청한 데이터를 적절하게 가공해서 컨트롤러로 넘기는 역할을 한다.
public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

}