package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 예제 6.9
// ProudctDAO 인터페이스의 구현체 클래스
// ProductDAOImpl 클래스를 스프링이 관리하는 빈으로 등록하기 위해
// @Component 또는 @Service 이노테이션을 지정해야한다.
// 빈으로 등록된 객체는 다른 클래스가 인터페이스를 가지고 의존성을 주입받을 때 이 구현체를 찾아 주입받게 된다.
@Component
public class ProductDAOImpl implements ProductDAO {

    // DAO 객체에서도 데이터베이스에 접근하기 위해 리포지토리 인터페이스를 사용해 의존성을 주입을 받아야한다.
    // 리포지토리를 정의하고 생성자를 통해 의존성 주입을 받으면 된다.
    private ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 예제 6.10
    // insertProduct() 메서드
    // 인터페이스에 정의한 메서드를 구현 - Product 엔티티를 데이터베이스 저장하는 기능을 수행
    // 리포지토리를 생성할 때 인터페이스에서 따로 메서드를 구현하지 않아도 JPA에서 기본 메서드를 제공하므로 save 메서드를 활용
    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    // 예제 6.11
    // selectProduct() 메서드
    // selectProduct() 메서드가 사용한 리포지토리 메서드는 getById()이다.
    // 리포지토리에서 단건 조회를 위한 기본 메서드로는 두 가지 제공한다.
    // getById() 메서드와 findById() 메서드이다. 두 메서는 조회한다는 기능 측면에서는 동일하지만 세부 내용이 다르다.
    // getById() 메서드를 호출하면 프락시 객체를 리턴한다.
    // 실제 쿼리는 프락시 객체를 통해 최초로 데이터에 접근하는 시점에 실행된다.
    // findById() 내부적으로 EntityManager()의 find() 메서드를 호출한다. 영속성 컨텍스트의캐시에서 값을 조회한 후 영속성 컨텍스트의 캐시에서 값을 조회한 후 영속성 컨텍스트에 값이 존재하지 않는다면 실제 데이터베이스에서 데이터를 조회한다.
    // 리턴 값으로 OPtional 객체를 전달한다.
    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct = productRepository.getById(number);

        return selectedProduct;
    }

    // 예제 6.14
    // updateProductName() 메서드
    // JPA에서 값을 갱신할때 Update라는 키워드를 사용하지 않는다.
    // 영속성 컨텍스트를 활용해 값을 갱신한다. find()메서드를 통해 데이터베이스에서 값을 가져오면 가져온 객체가 영속성 컨텍스트에 추가한다.
    // 영속성 컨텍스트가 유지되는 상태에서 객체의 값을 변경하고 다시 save()을 실행하면 JPA에서 더티 체크라고 하는 변경 감지를 수행한다.
    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }

        return updatedProduct;
    }

    // 예제 6.16
    // deleteProduct() 메서드
    // 삭제하고자 하는 레코드와 매핑된 영속 객체를 영속성 컨텍스트에 가져와야 한다. deleteProduct() 메서드는 findById() 메서드를 통해 가져오는 작업을 수행하고
    // delete() 메서드를 통해 해당 객체를 삭제하게끔 삭제 요청을 한다.
    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
}