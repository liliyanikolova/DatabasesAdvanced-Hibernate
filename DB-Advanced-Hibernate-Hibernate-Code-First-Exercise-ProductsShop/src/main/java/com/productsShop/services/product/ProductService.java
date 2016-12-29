package com.productsShop.services.product;

import com.productsShop.domain.dto.json.ProductBuyerDto;
import com.productsShop.domain.dto.json.ProductDto;
import com.productsShop.domain.dto.json.ProductSellersDto;

import java.util.List;

public interface ProductService {

    void crete(ProductDto productDto);

    List<ProductBuyerDto> findWithoutBuyer();

    ProductSellersDto findWithoutBuyerXml();
}
