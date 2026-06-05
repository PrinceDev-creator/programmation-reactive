package com.reactive.productdeep.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private Long id;

    private String title;

    private String description;

    private String category;

    private Double price;

    private Double discountPercentage;

    private Double rating;

    private String brand;

    private String thumbnail;

    private List<String> images;

    private Integer stock;
}
