package com.productsShop.services.category;


import com.productsShop.domain.dto.json.CategoryDto;
import com.productsShop.domain.dto.json.CategoryProductDto;

import java.util.List;

public interface CategoryService {

    void create(CategoryDto categoryDto);

    List<CategoryProductDto> getCategoryStats();
}
