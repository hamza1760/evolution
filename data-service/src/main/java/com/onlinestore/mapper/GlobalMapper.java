package com.onlinestore.mapper;

import com.onlinestore.dto.*;
import com.onlinestore.entity.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class GlobalMapper {

    @Autowired
    ModelMapper modelMapper;

    public ItemDTO itemToItemDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    public Item itemDTOToItem(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }

    public ShoppingCartDTO shoppingCartToShoppingCartDTO(ShoppingCart shoppingCart) {
        return modelMapper.map(shoppingCart, ShoppingCartDTO.class);
    }

    public ShoppingCart shoppingCartDTOToShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        return modelMapper.map(shoppingCartDTO, ShoppingCart.class);
    }
}
