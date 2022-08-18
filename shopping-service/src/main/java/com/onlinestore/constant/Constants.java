package com.onlinestore.constant;

public enum Constants {

    //Exception
    CART_NOT_FOUND("Cart not found"),
    ITEM_NOT_FOUND("Item not found"),
    //Status constant
    ACTIVE("Active"),
    DELETED("Deleted");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
