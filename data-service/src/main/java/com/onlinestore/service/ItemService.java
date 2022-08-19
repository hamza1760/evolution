package com.onlinestore.service;

import com.onlinestore.dto.*;

import java.util.*;

public interface ItemService {

    List<ItemDTO> getItemList();

    ItemDTO addItem(ItemDTO itemDTO);

    ItemDTO findItem(int itemId);

    void deleteItem(int itemId);
}
