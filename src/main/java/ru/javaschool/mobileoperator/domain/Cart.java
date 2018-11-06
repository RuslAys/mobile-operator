package ru.javaschool.mobileoperator.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    private int id;
    private String name = "";
    private int price;
    private int quantity;
    private List<CartItem> cartItems = new ArrayList<>();

    public void addItem(CartItem item){
        cartItems.add(item);
    }

    public void removeItem(CartItem item){
        if(!cartItems.isEmpty()){
            cartItems.removeIf(item1 -> item.equals(item1));
        }
    }

    public void updateItem(CartItem item){
        if(!cartItems.isEmpty()){
            for(CartItem item1: cartItems){
                if(item1.equals(item)){
                    cartItems.remove(item1);
                    cartItems.add(item);
                }
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id == cart.id &&
                price == cart.price &&
                quantity == cart.quantity &&
                Objects.equals(name, cart.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity);
    }
}
