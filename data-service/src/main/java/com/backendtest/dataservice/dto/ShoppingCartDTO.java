package com.backendtest.dataservice.dto;

import com.backendtest.dataservice.model.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ShoppingCartDTO {

    private UUID cartId;

    private Integer itemCount;

    private Double grandTotal;

    private Double totalDiscount;

    private List<ItemDTO> itemList;
}
