package com.onlinestore.service;

import com.onlinestore.client.DataService;
import com.onlinestore.dto.ItemDTO;

import com.onlinestore.dto.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private DataService dataService;

    @Override
    public ShoppingCartDTO createNewCart(ShoppingCartDTO shoppingCartDTO) {
        shoppingCartDTO.getItemList().forEach(orderedItem -> {

            double discountedPrice = orderedItem.getPrice() - (orderedItem.getQuantity() * (orderedItem.getQuantity() * (orderedItem.getPrice() * orderedItem.getDiscount())));

            if (shoppingCartDTO.getGrandTotal() == null) {
                shoppingCartDTO.setGrandTotal(orderedItem.getPrice() - discountedPrice);
                shoppingCartDTO.setTotalDiscount(discountedPrice);
            } else {
                shoppingCartDTO.setGrandTotal(shoppingCartDTO.getGrandTotal() + (orderedItem.getPrice() - discountedPrice));
                shoppingCartDTO.setTotalDiscount(shoppingCartDTO.getTotalDiscount() + discountedPrice);
            }
        });
        shoppingCartDTO.setItemCount(shoppingCartDTO.getItemList().size());
        return dataService.createNewCart(shoppingCartDTO);
    }

    @Override
    public ShoppingCartDTO getCart(ShoppingCartDTO shoppingCartDTO) {
        return null;
    }

    @Override
    public List<ItemDTO> getCartItems(String cartId) {
        return null;
    }

    @Override
    public ShoppingCartDTO updateCartItems(String cartId, ItemDTO itemsDTO) {
        return null;
    }

    @Override
    public ShoppingCartDTO removeCartItems(String cartId, String itemId) {
        return null;
    }

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }
}
