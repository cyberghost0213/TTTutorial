package com.springboot.jpa.data.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

// 예제 6.6, 예제 6.30, 6.31, 6.32, 6.33, 6.34, 6.35, 6.36, 6.37
@Entity                         // 해당 클래스가 엔티티임을 명시하기 위한 어노테이션이다. 클래스 자체는 테이블과 일대일로 매칭되며, 해당 클래스의 인스턴스는 매핑되는 테이블에서 하나의 레코드를 의미한다.
@Getter
@Setter
@NoArgsConstructor              // 매개변수가 없는 생성자를 자동 생성
@AllArgsConstructor             // 모든 필드를 매개변수로 갖는 생성자를 자동 생성
@EqualsAndHashCode              // 객체의 동등성과 동일성을 비교하는 연산 메서드를 생성한다.
@ToString(exclude = "name")     // 민감한 정보는 exclude 속성을 통해 자동생성에서 제외할 수 있다.
@Table(name = "product")        // @Table 어노테이션을 사용하는 경우는 테이블의 이름과 클래스의 이름을 다르게 지정해야 하는 경우이다. 대체로 자바의 명명법과 데이터베이스가 사용하는 명명법이 다르기 떄문에 자주 사용된다.
public class Product {

    @Id                         // 엔티티 클래스의 필드는 테이블의 칼럼과 매핑된다. @Id 어노테이션이 선언된 필드는 테이블의 기본값 역할로 사용된다. 모든 엔티티는 @Id 어노테이션이 필요하다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 일반적으로 @Id와 같이 사용된다. 이 어노테이션은 해당필드의 값을 어떤 방식으로 자동으로 생성할지 결정할 때 사용한다.***
    private Long number;

    @Column(nullable = false)   // @Column의 어노테이션은 필드에 몇가지 설정을 더할 때 사용한다.***
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
