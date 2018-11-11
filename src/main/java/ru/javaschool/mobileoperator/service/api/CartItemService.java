package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.*;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

public interface CartItemService {
    /**
     * Method to create cart item with desired fields
     * @param cartItemBuilder cart item builder with desired fields
     * @return cart item
     */
    CartItem createItem(CartItemBuilder cartItemBuilder);

    /**
     * Method to update cart item with desired fields
     * @param cartItem cart item to update
     * @param cartItemBuilder cart item builder with desired fields
     * @return updated cart item
     */
    CartItem updateItem(CartItem cartItem, CartItemBuilder cartItemBuilder);

    /**
     * Method to execute procedure in cart item
     * @param cartItem cart item
     */
    void proceed(CartItem cartItem);
}
