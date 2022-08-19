package com.onlinestore.entity;

import com.onlinestore.constant.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Proxy(lazy = false)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    private int quantity;
    private long upc;
    private String color;
    private double price;
    private double discount;
    private String status = Constants.ACTIVE.getValue();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;

    public Item() {
    }

    public Item(int itemId, int quantity, long upc, String color, double price, double discount, String status, ShoppingCart cart) {
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

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Item{" +
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
