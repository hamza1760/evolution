package com.onlinestore.service;

import com.onlinestore.client.*;
import com.onlinestore.constant.*;
import com.onlinestore.dto.*;
import com.onlinestore.exception.Exception;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    DataService dataService;

    @Override
    public List<ItemDTO> getItemList() {
        return dataService.getItemList();
    }

    @Override
    public ItemDTO addItem(ItemDTO itemDTO) {
        return dataService.addItem(itemDTO);
    }

    @Override
    public void addItemToCart(ItemDTO itemDTO, int cartId) {
        ItemDTO item = dataService.findItem(itemDTO.getItemId());
        if (item == null) {
            throw new Exception(Constants.ITEM_NOT_FOUND.getValue(), itemDTO.getItemId());
        }
        ShoppingCartDTO shoppingCartDTO = dataService.findShoppingCart(cartId);
        if (shoppingCartDTO == null) {
            throw new Exception(Constants.CART_NOT_FOUND.getValue(), cartId);
        }
        shoppingCartDTO.setItemCount(shoppingCartDTO.getItemCount() + 1);
        List<ItemDTO> itemListDTO = shoppingCartDTO.getItemList();

        itemListDTO.add(itemDTO);
        itemDTO.setQuantity(item.getQuantity() + 1);
        shoppingCartDTO.setGrandTotal(shoppingCartDTO.getGrandTotal() + (itemDTO.getPrice() * 1));
        shoppingCartDTO.setTotalDiscount(shoppingCartDTO.getTotalDiscount() + (itemDTO.getDiscount() * 1));
        shoppingCartDTO.setItemList(itemListDTO);
       dataService.addCart(shoppingCartDTO);
        itemDTO.setCart(shoppingCartDTO);
        dataService.addItem(itemDTO);
    }

    @Override
    public void updateItemInCart(ItemDTO itemDTO, int itemId) {
        ItemDTO item = dataService.findItem(itemDTO.getItemId());
        if (item == null) {
            throw new Exception(Constants.ITEM_NOT_FOUND.getValue(), itemDTO.getItemId());
        }
        ShoppingCartDTO shoppingCartDTO = item.getCart();
        List<ItemDTO> itemListDTO = shoppingCartDTO.getItemList();
        itemListDTO.forEach(itemInCart -> {
            if (itemInCart.getItemId() == itemId) {
                if (itemDTO.getItemId() == itemId) {
                    shoppingCartDTO.setGrandTotal(shoppingCartDTO.getGrandTotal() - (itemInCart.getPrice() * itemInCart.getQuantity()));
                    shoppingCartDTO.setTotalDiscount(shoppingCartDTO.getTotalDiscount() - (itemInCart.getDiscount() * itemInCart.getQuantity()));
                    itemDTO.setCart(shoppingCartDTO);
                    shoppingCartDTO.setGrandTotal(shoppingCartDTO.getGrandTotal() + (itemDTO.getPrice() * itemDTO.getQuantity()));
                    shoppingCartDTO.setTotalDiscount(shoppingCartDTO.getTotalDiscount() + (itemDTO.getDiscount() * itemDTO.getQuantity()));
                    dataService.addCart(shoppingCartDTO);
                    dataService.addItem(itemDTO);
                } else {
                    throw new Exception("Cannot update item id", itemDTO.getItemId());
                }
            }
        });
    }

    public void removeItemFromCart(int itemId) {
        ItemDTO item = dataService.findItem(itemId);
        if (item == null) {
            throw new Exception(Constants.ITEM_NOT_FOUND.getValue(), itemId);
        }
        ShoppingCartDTO shoppingCartDTO = item.getCart();
        shoppingCartDTO.setItemCount(shoppingCartDTO.getItemCount() - 1);
        List<ItemDTO> itemList = shoppingCartDTO.getItemList();
        itemList.forEach(itemInCart -> {
            if (itemInCart.getItemId() == itemId) {
                itemInCart.setQuantity(itemInCart.getQuantity() - 1);
                if (itemInCart.getQuantity() != 0) {
                    shoppingCartDTO.setGrandTotal(shoppingCartDTO.getGrandTotal() - (itemInCart.getPrice() * 1));
                    shoppingCartDTO.setTotalDiscount(shoppingCartDTO.getTotalDiscount() - (itemInCart.getDiscount() * 1));
                    dataService.addItem(itemInCart);
                    shoppingCartDTO.setItemList(itemList.stream().filter((items) -> items.getItemId() != itemId).collect(Collectors.toList()));
                    dataService.addCart(shoppingCartDTO);
                } else {
                    shoppingCartDTO.setGrandTotal(shoppingCartDTO.getGrandTotal() - (itemInCart.getPrice() * 1));
                    shoppingCartDTO.setTotalDiscount(shoppingCartDTO.getTotalDiscount() - (itemInCart.getDiscount() * 1));
                    itemInCart.setCart(null);
                    dataService.addItem(itemInCart);
                    shoppingCartDTO.setItemList(itemList.stream().filter((items) -> items.getItemId() != itemId).collect(Collectors.toList()));
                    dataService.addCart(shoppingCartDTO);
                }
            }
        });
    }

    public void deleteItemById(int itemId) {
        ItemDTO item = dataService.findItem(itemId);
        if (item == null) {
            throw new Exception(Constants.ITEM_NOT_FOUND.getValue(), itemId);
        }
        for (int i = 1; i <= item.getQuantity(); i++) {
            removeItemFromCart(itemId);
        }
        dataService.deleteItem(itemId);
    }
}
