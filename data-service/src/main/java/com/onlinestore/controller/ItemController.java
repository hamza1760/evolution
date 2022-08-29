package com.onlinestore.controller;

import com.onlinestore.apiresponse.*;
import com.onlinestore.dto.*;
import com.onlinestore.entity.*;
import com.onlinestore.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ItemController {

    @Autowired
    ItemServiceImpl itemService;

    @GetMapping("/item")
    public List<ItemDTO> getItemList() {
        return itemService.getItemList();
    }

    @PostMapping("/item")
    public ItemDTO saveItem(@RequestBody ItemDTO itemDTO) {
        return itemService.saveItem(itemDTO);
    }

    @PostMapping("/item/{cartId}")
    public ItemDTO addItemToCart(@RequestBody ItemDTO itemDTO,@PathVariable int cartId) {
        return itemService.addItemToCart(itemDTO,cartId);
    }

   @GetMapping("/item/{itemId}")
   public ItemDTO findItem(@PathVariable int itemId){
        return itemService.findItem(itemId);
   }

   @DeleteMapping("/item/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable int itemId){
        itemService.deleteItem(itemId);
        return new ResponseEntity<>(new ApiResponse("Item Deleted",itemId),HttpStatus.OK);
   }
}
