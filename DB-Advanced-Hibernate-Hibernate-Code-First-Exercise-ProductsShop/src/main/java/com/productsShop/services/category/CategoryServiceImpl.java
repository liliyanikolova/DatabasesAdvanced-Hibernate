package com.productsShop.services.category;

import com.productsShop.domain.dto.json.CategoryDto;
import com.productsShop.domain.dto.json.CategoryProductDto;
import com.productsShop.domain.entities.Category;
import com.productsShop.parser.ModelParser;
import com.productsShop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final ModelParser modelParser;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelParser modelParser) {
        this.categoryRepository = categoryRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(CategoryDto categoryDto) {
        Category category = this.modelParser.convert(categoryDto, Category.class);
        this.categoryRepository.save(category);
    }

    @Override
    public List<CategoryProductDto> getCategoryStats() {
        List<Object[]> categoryStats = this.categoryRepository.getCategoryStats();
        List<CategoryProductDto> categoryProductDtos = new ArrayList<>();
        for (Object[] categoryStat : categoryStats) {
            CategoryProductDto categoryProductDto = new CategoryProductDto();
            categoryProductDto.setCategory((String) categoryStat[0]);
            categoryProductDto.setProductsCount((Long) categoryStat[1]);
            categoryProductDto.setAveragePrice((Double) categoryStat[2]);
            categoryProductDto.setTotalRevenue((BigDecimal) categoryStat[3]);
            categoryProductDtos.add(categoryProductDto);
        }

        return categoryProductDtos;
    }
}
