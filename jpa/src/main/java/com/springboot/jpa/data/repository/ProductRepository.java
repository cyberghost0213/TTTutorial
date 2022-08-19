package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// 예제 6.6
// 리포지토리 생성
// 리포지토리는 Spring Data JPA가 제공하는 인터페이스이다.
// 엔티티를 데이터베이스의 테이블과 구조를 생성하는 데 사용했다면 리포지토리는 엔티티가 생성한 데이트베이스에 접근하는 데 사용한다.
// 리포지토리를 생성하기 위해서는 접근하려는 테이블과 매핑되는 엔티티에 대한 인터페이스를 생성하고, JpaRepository를 상속 받으면 된다.
public interface ProductRepository extends JpaRepository<Product, Long> {

}
