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
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    GlobalMapper globalMapper;

    @Override
    public ShoppingCartDTO addCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = shoppingCartRepository.save(globalMapper.shoppingCartDTOToShoppingCart(shoppingCartDTO));
        return globalMapper.shoppingCartToShoppingCartDTO(shoppingCart);
    }

    @Override
    public List<ShoppingCartDTO> viewCartList() {
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findByStatus(Constants.ACTIVE.getValue());
        List<ShoppingCartDTO> collect = shoppingCartList.stream().map(globalMapper::shoppingCartToShoppingCartDTO).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ShoppingCartDTO removeAllItemsFromCart(int cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByStatusAndCartId(Constants.ACTIVE.getValue(), cartId);
        if (shoppingCart == null) {
            throw new Exception(Constants.CART_NOT_FOUND.getValue(), cartId);
        }
        shoppingCart.setTotalDiscount(0.0);
        shoppingCart.setGrandTotal(0.0);
        shoppingCart.setItemCount(0);
        List<Item> itemList = shoppingCart.getItemList();
        itemList.forEach(item -> {
            item.setQuantity(0);
            item.setCart(null);
            itemRepository.save(item);
        });
        shoppingCart.setItemList(null);
        shoppingCartRepository.save(shoppingCart);
        return globalMapper.shoppingCartToShoppingCartDTO(shoppingCart);
    }

    public void deleteShoppingCartById(int cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByStatusAndCartId(Constants.ACTIVE.getValue(), cartId);
        if (shoppingCart == null) {
            throw new Exception(Constants.CART_NOT_FOUND.getValue(), cartId);
        }
        removeAllItemsFromCart(cartId);
        shoppingCartRepository.delete(Constants.DELETED.getValue(), cartId);
    }
}
