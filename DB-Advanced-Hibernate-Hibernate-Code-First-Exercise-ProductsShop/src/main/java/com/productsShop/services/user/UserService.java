package com.productsShop.services.user;

import com.productsShop.domain.dto.json.SellerDto;
import com.productsShop.domain.dto.json.UserDto;

import java.util.List;

public interface UserService{

    void create(UserDto userDto);

    List<SellerDto> findWithMoreThanOneBuyer();
}
