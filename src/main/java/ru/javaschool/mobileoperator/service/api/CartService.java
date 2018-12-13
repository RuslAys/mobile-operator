package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.domain.CartResult;

public interface CartService {
    /**
     * Method to add item to cart
     * @param cart cart
     * @param item cart item
     */
    void addItem(Cart cart, CartItem item);

    /**
     * Method to remove item from cart
     * @param cart cart
     * @param item cart item
     */
    void removeItem(Cart cart, CartItem item);

    /**
     * Method to update item in cart
     * @param cart cart
     * @param item cart item
     */
    void updateItem(Cart cart, CartItem item);

    /**
     * Method to confirm all items and start executing items` procedure
     * @param cart cart
     */
    void confirm(Cart cart, CartResult cartResult);
}
