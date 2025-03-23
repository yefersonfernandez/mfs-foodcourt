package com.pragma.plazoleta.application.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishResponseDto {
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private RestaurantResponseDto restaurantResponseDto;
    private CategoryResponseDto categoryResponseDto;
}
