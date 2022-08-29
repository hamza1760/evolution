package com.onlinestore.service;

import com.onlinestore.client.*;
import com.onlinestore.constant.*;
import com.onlinestore.dto.*;
import com.onlinestore.exception.Exception;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    DataService dataService;

    @Override
    public ShoppingCartDTO addCart(ShoppingCartDTO shoppingCartDTO) {
        return dataService.saveCart(shoppingCartDTO);
    }

    @Override
    public List<ShoppingCartDTO> viewCartList() {
        return dataService.viewCartList();
    }

    @Override
    public ShoppingCartDTO removeAllItemsFromCart(int cartId) {
        ShoppingCartDTO shoppingCartDTO = dataService.findShoppingCart(cartId);
        if (shoppingCartDTO == null) {
            throw new Exception(Constants.CART_NOT_FOUND.getValue(), cartId);
        }
        shoppingCartDTO.setTotalDiscount(0.0);
        shoppingCartDTO.setGrandTotal(0.0);
        shoppingCartDTO.setItemCount(0);
        List<ItemDTO> itemListDTO = shoppingCartDTO.getItemList();
        itemListDTO.forEach(itemDTO -> {
            itemDTO.setQuantity(0);
            itemDTO.setCart(null);
            dataService.saveItem(itemDTO);
        });
        shoppingCartDTO.setItemList(null);
        shoppingCartDTO = dataService.saveCart(shoppingCartDTO);
        return shoppingCartDTO;
    }

    public void deleteShoppingCartById(int cartId) {
        ShoppingCartDTO shoppingCartDTO = dataService.findShoppingCart(cartId);
        if (shoppingCartDTO == null) {
            throw new Exception(Constants.CART_NOT_FOUND.getValue(), cartId);
        }
        removeAllItemsFromCart(cartId);
        dataService.deleteShoppingCart(cartId);
    }
}
