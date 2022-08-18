package com.backendtest.shoppingservice.service;

import com.backendtest.shoppingservice.dto.ItemDTO;
import com.backendtest.shoppingservice.dto.ShoppingCartDTO;


import java.util.List;

public interface ShoppingService {
    ShoppingCartDTO createNewCart(ShoppingCartDTO shoppingCartDTO);

    ShoppingCartDTO getCart(ShoppingCartDTO shoppingCartDTO);

    List<ItemDTO> getCartItems(String cartId);

    ShoppingCartDTO updateCartItems(String cartId, ItemDTO itemsDTO);

    ShoppingCartDTO removeCartItems(String cartId, String itemId);

}
