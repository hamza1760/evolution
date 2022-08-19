package com.onlinestore.service;

import com.onlinestore.dto.*;

import java.util.*;

public interface ShoppingCartService {

    List<ShoppingCartDTO> viewCartList();
    
    ShoppingCartDTO addCart(ShoppingCartDTO shoppingCartDTO);

    ShoppingCartDTO findShoppingCart(int shoppingCartId);

    ShoppingCartDTO saveShoppingCart(ShoppingCartDTO shoppingCartDTO);

    void deleteShoppingCart(int shoppingCartId);
}
