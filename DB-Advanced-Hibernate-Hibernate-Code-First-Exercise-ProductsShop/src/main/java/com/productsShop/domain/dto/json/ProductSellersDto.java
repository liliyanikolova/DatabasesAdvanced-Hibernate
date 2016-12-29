package com.productsShop.domain.dto.json;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSellersDto implements Serializable{

    @XmlElement(name = "product")
    List<ProductSellerDto> productSellerDtos;

    public ProductSellersDto() {
        this.setProductSellerDtos(new ArrayList<>());
    }

    public List<ProductSellerDto> getProductSellerDtos() {
        return productSellerDtos;
    }

    public void setProductSellerDtos(List<ProductSellerDto> productSellerDtos) {
        this.productSellerDtos = productSellerDtos;
    }

    public void addProductSellerDto(ProductSellerDto productSellerDto){
        this.productSellerDtos.add(productSellerDto);
    }
}
