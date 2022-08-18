package com.onlinestore.service;

import com.onlinestore.dto.ItemDTO;
import com.onlinestore.dto.ShoppingCartDTO;


import java.util.List;

public interface ShoppingService {
    ShoppingCartDTO createNewCart(ShoppingCartDTO shoppingCartDTO);

    ShoppingCartDTO getCart(ShoppingCartDTO shoppingCartDTO);

    List<ItemDTO> getCartItems(String cartId);

    ShoppingCartDTO updateCartItems(String cartId, ItemDTO itemsDTO);

    ShoppingCartDTO removeCartItems(String cartId, String itemId);

}
