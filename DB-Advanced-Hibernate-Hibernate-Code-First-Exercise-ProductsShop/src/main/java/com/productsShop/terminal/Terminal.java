package com.productsShop.terminal;

import com.productsShop.domain.dto.json.*;
import com.productsShop.parser.JsonParser;
import com.productsShop.parser.XMLParser;
import com.productsShop.services.category.CategoryService;
import com.productsShop.services.product.ProductService;
import com.productsShop.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner{

    @Autowired
    private JsonParser jsonParser;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private XMLParser xmlParser;

    @Override
    public void run(String... strings) throws Exception {
        this.parseJsonUsers();
        this.parseJsonCategory();
        this.parseJsonProducts();
        this.exportBuyerDtos();
        this.exportSellersDtos();
        this.exportCategoryStats();
        this.exportXmlProducts();
    }

    private void parseJsonUsers(){
        UserDto[] userDtos = null;
        try {
            userDtos = this.jsonParser.importJson(UserDto[].class, "/files/input/users.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (UserDto userDto : userDtos) {
            try {
                this.userService.create(userDto);
            } catch (Exception e){
                System.out.println("Invalid User");
            }
        }
    }

    private void parseJsonCategory(){
        CategoryDto[] categoryDtos = null;
        try {
            categoryDtos = this.jsonParser.importJson(CategoryDto[].class, "/files/input/categories.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (CategoryDto categoryDto : categoryDtos) {
            try {
                this.categoryService.create(categoryDto);
            } catch (Exception e){
                System.out.println("Invalid Category");
            }
        }
    }

    private void parseJsonProducts(){
        ProductDto[] productDtos = null;
        try {
            productDtos = this.jsonParser.importJson(ProductDto[].class, "/files/input/products.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (ProductDto productDto : productDtos) {
            try {
                this.productService.crete(productDto);
            } catch (Exception e){
                System.out.println("Invalid Product");
            }
        }
    }

    private void exportBuyerDtos() {
        List<ProductBuyerDto> productBuyerDtos = this.productService.findWithoutBuyer();
        try {
            this.jsonParser.exportJson(productBuyerDtos, "src/main/resources/files/output/buyers.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportSellersDtos(){
        List<SellerDto> sellerDtos = this.userService.findWithMoreThanOneBuyer();
        try {
            this.jsonParser.exportJson(sellerDtos, "src/main/resources/files/output/sellers.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportCategoryStats(){
        List<CategoryProductDto> categoryProductDtos = this.categoryService.getCategoryStats();
        try {
            this.jsonParser.exportJson(categoryProductDtos, "src/main/resources/files/output/categoryStats.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportXmlProducts(){
        ProductSellersDto productSellersDto = this.productService.findWithoutBuyerXml();
        try {
            this.xmlParser.write(productSellersDto, "src/main/resources/files/output/products.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
