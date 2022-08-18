package com.backendtest.shoppingservice.client;


import com.backendtest.shoppingservice.dto.ShoppingCartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "data-service", url = "localhost:8081")
public interface DataService {

    @PostMapping("/cart")
    ShoppingCartDTO createNewCart(@RequestBody ShoppingCartDTO shoppingCartDTO);

}
