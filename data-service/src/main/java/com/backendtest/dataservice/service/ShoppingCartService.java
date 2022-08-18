package com.backendtest.dataservice.service;

import com.backendtest.dataservice.dto.ShoppingCartDTO;

import java.util.UUID;

public interface ShoppingCartService {

    ShoppingCartDTO removeCartItems(UUID itemId);

    ShoppingCartDTO createNewCart(ShoppingCartDTO shoppingCartDTO);
}
