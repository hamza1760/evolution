package com.backendtest.dataservice.cotroller;

import com.backendtest.dataservice.dto.ShoppingCartDTO;
import com.backendtest.dataservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @PostMapping
    ShoppingCartDTO createNewCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return shoppingCartService.createNewCart(shoppingCartDTO);
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
}
