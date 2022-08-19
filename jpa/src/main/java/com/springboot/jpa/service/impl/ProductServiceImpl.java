package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.service.ProductService;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 예제 6.21
// 서비스 인터페이스 구현체 클래스
@Service
public class ProductServiceImpl implements ProductService {


    private final ProductDAO productDAO;

    @Autowired
    // 인터페이스 구현체 클래스에서는 4~9번 줄과 같이 DAO 인터페이스를 선언하고 @Autowired를 지정한 성생저를 통해 의존성을 주입 받았다.
    // 그리고 인터페이스에서 정의한 메서드를 오버라이딩한다.

    // 현재 서비스 레이어는 DTO 객체와 앤티티 객체가 공존하도록 설계되어있어 변환이 필요하다.
    // DTO 객체에 값을 넣고 초기화 작업을 수행 - 빌더 패턴을 이용하거나 엔티티 객체나 DTO 객체 내부에 변환하는 메서드를 추가해서 간단하게 전환한다.
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    // 예제 6.22
    // getProduct() 메서드 구현
    // 조회 메서드에 해당하는 getProduct() 메서드를 구현했다.

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    // 예제 6.23
    // saveProduct() 메서드 구현
    // 저장메서드에 해당하는 saveProduct()를 구현
    // 전달받은 DTO 객체를 통해 엔티티 객체를 생성해서 초기화한 후 DAO객체로 전달하면 된다.
    // 저장 메서드의 리턴 타입을 어떻게 지정할지 고민해야 한다.
    // 일반적으로 void 타입으로 작성하거나 boolean 타입으로 지정한다.
    // saveProduct() 메서드는 상품 정보를 전달하고 애플리케이션을 거쳐 데이터베이스에 저장하는 역할을 수행한다.


    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productDAO.insertProduct(product);

        // 현재 데이터를 조회하는 메서드는 데이터베이스에서 인덱스 값을 통해 찾아야 하는데, void로 저장 메서드를 구현할 경우 알 방법이 잆다.
        // 그렇기 때문에 데이터 저장을 하면서 가져온 인덱스를 DTO에 담아 결괏값으로 클라이언트에 전달하는 코드를 작성했다.
        // 만약 이 같은 방식이 아니라 void 형식으로 메서드를 작성한다면 조회 메서드를 추가로 구현하고 클라이언트에서도 한 번 더 요청해야한다.
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    // 예제 6.24
    // changeProductName() 메서드 구현
    // changeProductName() 메서드는 상품정보 중 이름을 변경하는 작업을 수행한다.
    // 이름을 변경하기 위해 먼저 클라이언트로부터 대상을 식별할 수 있는 인덱스 값과 변경하려는 이름을 받아온다.
    // 좀 더 견고하게 코드를 작성하기 위해 기존 이름도 받아와서 식별자로 가져온 상품정보와 일치하는지 확인하는 단계도 추가한다.
    // 이 기능의 핵심이 되는 비즈니스 로직은 레코드의 이름 칼럼을 변경시키는 것이다. ㅣㄹ제 레코드 값을 변경하는 작업은 DAO에서 진행하기 때문에
    // 서비스 레이어에서는 해당 메서드를 호출해서 결괏값만 받아온다.

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product changedProduct = productDAO.updateProductName(number, name);



        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());

        return productResponseDto;
    }

    // 예제 6.25
    // deleteProduc() 구현
    // 상품정보를 삭제하는 메서드는 리포지토리에서 제공하는 delete() 메서들ㄹ 사용할 경우 리턴받는 타입이 지정되어 있지 않기 때문에 void로 지정해 메서드를 구현한다.
    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }
}
