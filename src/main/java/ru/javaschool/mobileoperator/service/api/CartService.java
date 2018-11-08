package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;

public interface CartService {
    void addItem(Cart cart, CartItem item);
    void removeItem(Cart cart, CartItem item);
    void updateItem(Cart cart, CartItem item);
    void confirm(Cart cart);
}
