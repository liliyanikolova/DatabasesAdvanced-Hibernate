package com.productsShop.services.product;

import com.productsShop.domain.dto.json.ProductBuyerDto;
import com.productsShop.domain.dto.json.ProductDto;
import com.productsShop.domain.dto.json.ProductSellerDto;
import com.productsShop.domain.dto.json.ProductSellersDto;
import com.productsShop.domain.entities.Category;
import com.productsShop.domain.entities.Product;
import com.productsShop.domain.entities.User;
import com.productsShop.parser.ModelParser;
import com.productsShop.repositories.CategoryRepository;
import com.productsShop.repositories.ProductRepository;
import com.productsShop.repositories.UserRepository;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ModelParser modelParser;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ModelParser modelParser,
                              UserRepository userRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.modelParser = modelParser;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void crete(ProductDto productDto) {
        Product product = this.modelParser.convert(productDto, Product.class);
        product.setBuyer(this.getRandomUser());
        product.setSeller(this.getRandomUser());
        product.setCategories(this.getRandomCategories());

        this.productRepository.save(product);
    }

    @Override
    public List<ProductBuyerDto> findWithoutBuyer() {
        List<Product> products = this.productRepository.findWithoutBuyer();
        List<ProductBuyerDto> productBuyerDtos = this.modelParser.convert(products, ProductBuyerDto.class);

        return productBuyerDtos;
    }

    @Override
    public ProductSellersDto findWithoutBuyerXml() {
        List<Product> products = this.productRepository.findWithoutBuyer();
        ProductSellersDto productSellersDto = new ProductSellersDto();
        for (Product product : products) {
//            PropertyMap<Product, ProductSellerDto> propertyMap = new PropertyMap<Product, ProductSellerDto>() {
//                @Override
//                protected void configure() {
//                    map(source.getBuyer().getFirstName(), destination.getBuyerFirstName());
//                    map(source.getBuyer().getLastName(), destination.getBuyerLastName());
//                }
//            };

//            ProductSellerDto productSellerDto = this.modelParser.convert(product, ProductSellerDto.class, propertyMap);
            ProductSellerDto productSellerDto = this.modelParser.convert(product, ProductSellerDto.class);
            productSellersDto.addProductSellerDto(productSellerDto);
        }

        return productSellersDto;
    }

    private User getRandomUser(){
        long userCount = this.userRepository.count();
        long randomId = ThreadLocalRandom.current().nextLong(1, userCount + 100);
        User user = this.userRepository.findOne(randomId);
        return user;
    }

    private Set<Category> getRandomCategories(){
        Set<Category> categories = new HashSet<>();
        int listSize = ThreadLocalRandom.current().nextInt(1, 5);
        long categoriesCount = this.categoryRepository.count();
        for (int i = 0; i < listSize; i++) {
            long randomCategoryId = ThreadLocalRandom.current().nextLong(1, categoriesCount + 1);
            categories.add(this.categoryRepository.findOne(randomCategoryId));
        }

        return categories;
    }
}
