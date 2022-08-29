package com.onlinestore.service;

import com.onlinestore.dto.*;

import java.util.*;

public interface ItemService {

    List<ItemDTO> getItemList();

    ItemDTO saveItem(ItemDTO itemDTO);

    ItemDTO addItemToCart(ItemDTO itemDTO,int cartId);

    ItemDTO findItem(int itemId);

    void deleteItem(int itemId);
}
