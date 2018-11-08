package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.*;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

public interface CartItemService {
    CartItem createItem(CartItemBuilder cartItemBuilder);
    CartItem updateItem(CartItem cartItem, CartItemBuilder cartItemBuilder);
    void proceed(CartItem cartItem);
}
