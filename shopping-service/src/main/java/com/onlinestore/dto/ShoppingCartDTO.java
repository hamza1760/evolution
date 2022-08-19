package com.onlinestore.dto;

import com.fasterxml.jackson.annotation.*;
import com.onlinestore.constant.*;

import java.util.*;

public class ShoppingCartDTO {

    private int cartId;
    private int itemCount;
    private double grandTotal;
    private double totalDiscount;
    @JsonIgnore
    private String status = Constants.ACTIVE.getValue();

    private List<ItemDTO> itemList;

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(int cartId, int itemCount, double grandTotal, double totalDiscount, String status, List<ItemDTO> itemList) {
        this.cartId = cartId;
        this.itemCount = itemCount;
        this.grandTotal = grandTotal;
        this.totalDiscount = totalDiscount;
        this.status = status;
        this.itemList = itemList;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemDTO> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "cartId=" + cartId +
                ", itemCount=" + itemCount +
                ", grandTotal=" + grandTotal +
                ", totalDiscount=" + totalDiscount +
                ", status='" + status + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
