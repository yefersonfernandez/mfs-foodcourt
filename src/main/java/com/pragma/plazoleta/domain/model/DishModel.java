package com.pragma.plazoleta.domain.model;

public class DishModel {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private Boolean active;
    private RestaurantModel restaurantModel;
    private CategoryModel categoryModel;

    public DishModel() {
    }

    public DishModel(Long id, String name, Integer price, String description, String imageUrl, Boolean active, RestaurantModel restaurantModel, CategoryModel categoryModel) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.active = active;
        this.restaurantModel = restaurantModel;
        this.categoryModel = categoryModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }
}
