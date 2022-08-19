package com.springboot.jpa.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 예제 6.19
// ProductResponseDto 클래스

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponseDto {

    private Long number;

    private String name;

    private int price;

    private int stock;

}