package com.onlinestore.service;

import com.onlinestore.dto.*;

import java.util.*;

public interface ItemService {

    List<ItemDTO> getItemList();

    ItemDTO addItem(ItemDTO itemDTO);

    ItemDTO findItem(int itemId);

    ItemDTO saveItem(ItemDTO itemDTO);

    void deleteItem(int itemId);
}
