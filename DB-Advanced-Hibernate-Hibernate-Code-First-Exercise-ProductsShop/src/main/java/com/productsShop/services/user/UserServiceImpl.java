package com.productsShop.services.user;


import com.productsShop.domain.dto.json.SellerDto;
import com.productsShop.domain.dto.json.UserDto;
import com.productsShop.domain.entities.User;
import com.productsShop.parser.ModelParser;
import com.productsShop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final ModelParser modelParser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelParser modelParser) {
        this.userRepository = userRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(UserDto userDto) {
        User user = this.modelParser.convert(userDto, User.class);
        this.userRepository.save(user);
    }

    @Override
    public List<SellerDto> findWithMoreThanOneBuyer() {
        List<User> sellers = this.userRepository.findWithMoreThanOneBuyer();
        List<SellerDto> sellerDtos = this.modelParser.convert(sellers, SellerDto.class);

        return sellerDtos;
    }
}
