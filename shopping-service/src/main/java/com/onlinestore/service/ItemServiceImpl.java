package com.onlinestore.service;

import com.onlinestore.client.*;
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
    private DataService dataService;

    @Override
    public List<ItemDTO> getItemList() {
       return dataService.getItemList();
    }

    @Override
    public ItemDTO addItem(ItemDTO itemDTO) {
        itemDTO = dataService.addItem(itemDTO);
        return itemDTO;
    }

    @Override
    public void addItemToCart(ItemDTO itemDTO, int cartId) {
        Item item = itemRepository.findByStatusAndItemId(Constants.ACTIVE.getValue(), itemDTO.getItemId());
        if (item == null) {
            throw new Exception(Constants.ITEM_NOT_FOUND.getValue(), itemDTO.getItemId());
        }
        ShoppingCart shoppingCart = shoppingCartRepository.findByStatusAndCartId(Constants.ACTIVE.getValue(), cartId);
        if (shoppingCart == null) {
            throw new Exception(Constants.CART_NOT_FOUND.getValue(), cartId);
        }
        shoppingCart.setItemCount(shoppingCart.getItemCount()+1);
        List<Item> itemList = shoppingCart.getItemList();
        itemList.add(globalMapper.itemDTOToItem(itemDTO));
            itemDTO.setQuantity(item.getQuantity() + 1);
            shoppingCart.setGrandTotal(shoppingCart.getGrandTotal() + (itemDTO.getPrice() * 1));
            shoppingCart.setTotalDiscount(shoppingCart.getTotalDiscount() + (itemDTO.getDiscount() * 1));
            shoppingCart.setItemList(itemList);
            shoppingCartRepository.save(shoppingCart);
            itemDTO.setCart(globalMapper.shoppingCartToShoppingCartDTO(shoppingCart));
            itemRepository.save(globalMapper.itemDTOToItem(itemDTO));
    }

    @Override
    public void updateItemInCart(ItemDTO itemDTO, int itemId) {
        Item item = itemRepository.findByStatusAndItemId(Constants.ACTIVE.getValue(), itemId);
        if (item == null) {
            throw new Exception(Constants.ITEM_NOT_FOUND.getValue(), itemId);
        }
        ShoppingCart shoppingCart = item.getCart();
        if (shoppingCart == null) {
            throw new Exception(Constants.CART_NOT_FOUND.getValue() + " in item with given itemId", itemId);
        }
        List<Item> itemList = shoppingCart.getItemList();
        itemList.forEach(itemInCart -> {
            if (itemInCart.getItemId() == itemId) {
                if (itemDTO.getItemId() == itemId) {
                    shoppingCart.setGrandTotal(shoppingCart.getGrandTotal() - (itemInCart.getPrice() * itemInCart.getQuantity()));
                    shoppingCart.setTotalDiscount(shoppingCart.getTotalDiscount() - (itemInCart.getDiscount() * itemInCart.getQuantity()));
                    itemDTO.setCart(globalMapper.shoppingCartToShoppingCartDTO(itemInCart.getCart()));
                    shoppingCart.setGrandTotal(shoppingCart.getGrandTotal() + (itemDTO.getPrice() * itemDTO.getQuantity()));
                    shoppingCart.setTotalDiscount(shoppingCart.getTotalDiscount() + (itemDTO.getDiscount() * itemDTO.getQuantity()));
                    shoppingCartRepository.save(shoppingCart);
                    itemRepository.save(globalMapper.itemDTOToItem(itemDTO));
                } else {
                    throw new Exception("Cannot update item id", itemDTO.getItemId());
                }
            }
        });
    }

    public void removeItemFromCart(int itemId) {

        Item item = itemRepository.findByStatusAndItemId(Constants.ACTIVE.getValue(), itemId);
        if (item == null) {
            throw new Exception(Constants.ITEM_NOT_FOUND.getValue(), itemId);
        }
        ShoppingCart shoppingCart = item.getCart();
        if (shoppingCart == null) {
            throw new Exception(Constants.CART_NOT_FOUND.getValue() + " in item with given itemId", itemId);
        }
        shoppingCart.setItemCount(shoppingCart.getItemCount()-1);
        List<Item> itemList = shoppingCart.getItemList();
        itemList.forEach(itemInCart -> {
            if (itemInCart.getItemId() == itemId) {
                itemInCart.setQuantity(itemInCart.getQuantity() - 1);
                if(itemInCart.getQuantity()!=0) {
                    shoppingCart.setGrandTotal(shoppingCart.getGrandTotal() - (itemInCart.getPrice() * 1));
                    shoppingCart.setTotalDiscount(shoppingCart.getTotalDiscount() - (itemInCart.getDiscount() * 1));
                    itemRepository.save(itemInCart);
                    shoppingCart.setItemList(itemList.stream().filter((items) -> items.getItemId() != itemId).collect(Collectors.toList()));
                    shoppingCartRepository.save(shoppingCart);
                }
                else{
                    shoppingCart.setGrandTotal(shoppingCart.getGrandTotal() - (itemInCart.getPrice() * 1));
                    shoppingCart.setTotalDiscount(shoppingCart.getTotalDiscount() - (itemInCart.getDiscount() * 1));
                    itemInCart.setCart(null);
                    itemRepository.save(itemInCart);
                    shoppingCart.setItemList(itemList.stream().filter((items) -> items.getItemId() != itemId).collect(Collectors.toList()));
                    shoppingCartRepository.save(shoppingCart);
                }

            }
        });
    }

    public void deleteItemById(int itemId) {
        Item item = itemRepository.findByStatusAndItemId(Constants.ACTIVE.getValue(), itemId);
        if (item == null) {
            throw new Exception(Constants.ITEM_NOT_FOUND.getValue(), itemId);
        }
        for(int i =1 ; i<=item.getQuantity();i++) {
            removeItemFromCart(itemId);
        }
        itemRepository.delete(Constants.DELETED.getValue(), itemId);
    }
}
