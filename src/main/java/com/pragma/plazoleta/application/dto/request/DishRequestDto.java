package com.pragma.plazoleta.application.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishRequestDto {
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private Long restaurantId;
    private Long categoryId;
}
