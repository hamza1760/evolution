package com.onlinestore.service;

import com.onlinestore.constant.*;
import com.onlinestore.dto.*;
import com.onlinestore.entity.*;
import com.onlinestore.mapper.*;
import com.onlinestore.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    GlobalMapper globalMapper;

    @Override
    public List<ItemDTO> getItemList() {
        List<Item> items = itemRepository.findByStatus(Constants.ACTIVE.getValue());
        return items.stream().map(globalMapper::itemToItemDTO).collect(Collectors.toList());
    }

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        Item item = itemRepository.save(globalMapper.itemDTOToItem(itemDTO));
        return globalMapper.itemToItemDTO(item);
    }
    @Override
    public ItemDTO addItemToCart(ItemDTO itemDTO,int cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByStatusAndCartId(Constants.ACTIVE.getValue(), cartId);
        itemDTO.setCart(globalMapper.shoppingCartToShoppingCartDTO(shoppingCart));
        Item item = itemRepository.save(globalMapper.itemDTOToItem(itemDTO));
        return globalMapper.itemToItemDTO(item);
    }

    @Override
    public ItemDTO findItem(int itemId) {
        Item item = itemRepository.findByStatusAndItemId(Constants.ACTIVE.getValue(), itemId);
        if (item == null) {
            return null;
        }
        return globalMapper.itemToItemDTO(item);
    }

    @Override
    public void deleteItem(int itemId) {
        Item item = itemRepository.findByStatusAndItemId(Constants.ACTIVE.getValue(), itemId);
        if (item == null) {
        }
        itemRepository.delete(Constants.DELETED.getValue(), itemId);
    }
}
