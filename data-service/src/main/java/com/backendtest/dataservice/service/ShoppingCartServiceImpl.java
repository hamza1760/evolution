package com.backendtest.dataservice.service;

import com.backendtest.dataservice.dto.ItemDTO;
import com.backendtest.dataservice.dto.ShoppingCartDTO;
import com.backendtest.dataservice.model.Item;
import com.backendtest.dataservice.model.ShoppingCart;
import com.backendtest.dataservice.repositery.ItemRepository;
import com.backendtest.dataservice.repositery.ShoppingCartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ItemRepository itemRepository;

    private ShoppingCartRepository shoppingCartRepository;


    ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public ShoppingCartDTO createNewCart(ShoppingCartDTO shoppingCartDTO) {

        List<ItemDTO> items = new ArrayList<>();

        shoppingCartDTO.getItemList().forEach(orderItem -> {

            items.add(modelMapper.map(itemRepository.save(modelMapper.map(orderItem, Item.class)), ItemDTO.class));
        });

        shoppingCartDTO.setItemList(items);
        return modelMapper.map(shoppingCartRepository.save(modelMapper.map(shoppingCartDTO, ShoppingCart.class)), ShoppingCartDTO.class);
    }

    @Override
    public ShoppingCartDTO removeCartItems(UUID itemId) {
        return null;
    }


    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    public void setShoppingCartRepository(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

}
