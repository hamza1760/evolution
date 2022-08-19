package com.onlinestore.dto;

import com.fasterxml.jackson.annotation.*;
import com.onlinestore.constant.*;


public class ItemDTO {

    private int itemId;
    private int quantity;
    private long upc;
    private String color;
    private double price;
    private double discount;
    @JsonIgnore
    private String status = Constants.ACTIVE.getValue();

    @JsonIgnore
    private ShoppingCartDTO cart;

    public ItemDTO() {
    }

    public ItemDTO(int itemId, int quantity, long upc, String color, double price, double discount, String status, ShoppingCartDTO cart) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.upc = upc;
        this.color = color;
        this.price = price;
        this.discount = discount;
        this.status = status;
        this.cart = cart;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getUpc() {
        return upc;
    }

    public void setUpc(long upc) {
        this.upc = upc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ShoppingCartDTO getCart() {
        return cart;
    }

    public void setCart(ShoppingCartDTO cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemId=" + itemId +
                ", quantity=" + quantity +
                ", upc=" + upc +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", status='" + status + '\'' +
                ", cart=" + cart +
                '}';
    }
}
