package com.onlinestore.service;

import com.onlinestore.constant.*;
import com.onlinestore.dto.*;
import com.onlinestore.entity.*;
import com.onlinestore.exception.Exception;
import com.onlinestore.mapper.*;
import com.onlinestore.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    GlobalMapper globalMapper;

    @Override
    public List<ShoppingCartDTO> viewCartList() {
        List<ShoppingCart> shoppingCart = shoppingCartRepository.findByStatus(Constants.ACTIVE.getValue());
        return shoppingCart.stream().map(globalMapper::shoppingCartToShoppingCartDTO).collect(Collectors.toList());
    }

    @Override
    public ShoppingCartDTO addCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = shoppingCartRepository.save(globalMapper.shoppingCartDTOToShoppingCart(shoppingCartDTO));
        return globalMapper.shoppingCartToShoppingCartDTO(shoppingCart);
    }

    @Override
    public ShoppingCartDTO findShoppingCart(int cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByStatusAndCartId(Constants.ACTIVE.getValue(), cartId);
        if (shoppingCart== null) {
            return null;
        }
        return globalMapper.shoppingCartToShoppingCartDTO(shoppingCart);
    }


    @Override
    public void deleteShoppingCart(int cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByStatusAndCartId(Constants.ACTIVE.getValue(), cartId);
        if (shoppingCart== null) {

        }
        shoppingCartRepository.delete(Constants.DELETED.getValue(), cartId);
    }
}
