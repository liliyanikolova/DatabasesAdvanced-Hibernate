package com.productsShop.domain.dto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Set;

public class SellerDto implements Serializable{

    @Expose
    private String firstName;

    @Expose
    private  String lastName;

    @Expose
    private Set<ProductSellerDto> soldProducts;

    public SellerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductSellerDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductSellerDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
