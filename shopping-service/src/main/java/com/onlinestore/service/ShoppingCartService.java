package com.onlinestore.service;

import com.onlinestore.dto.*;

import java.util.*;

public interface ShoppingCartService {

    ShoppingCartDTO addCart(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCartDTO> viewCartList();

    void deleteShoppingCartById(int cartId);

    ShoppingCartDTO removeAllItemsFromCart(int cartId);
}
