package com.onlinestore.service;

import com.onlinestore.dto.*;

import java.util.*;

public interface ItemService {

    List<ItemDTO> getItemList();

    ItemDTO addItem(ItemDTO itemDTO);

    void addItemToCart(ItemDTO itemDTO,int cartId);

    void updateItemInCart(ItemDTO itemDTO, int itemId);

    void removeItemFromCart(int itemId);

    void deleteItemById(int itemId);
}
