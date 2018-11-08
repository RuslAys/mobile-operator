package ru.javaschool.mobileoperator.utils;

import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;

import javax.servlet.http.HttpSession;

public class CartHelper {
    public boolean alreadyExist(Cart cart, CartItem cartItem){
        switch (cartItem.getOperationType()){
            case SALE:
                for (CartItem cartItem1: cart.getCartItems()){
                    if (cartItem.getCustomer().equals(cartItem1.getCustomer())
                            && cartItem.getPhoneNumberId() == cartItem1.getPhoneNumberId()) {
                        return true;
                    }
                }
                break;
            case ADD_LOCK:
                return lockConflict(cart, cartItem);
            case REMOVE_LOCK:
                return lockConflict(cart, cartItem);
            case ADD_OPTION:
                return optionConflict(cart, cartItem);
            case REMOVE_OPTION:
                return optionConflict(cart, cartItem);
            case CHANGE_TARIFF:
                for (CartItem cartItem1: cart.getCartItems()){
                    if (cartItem.getTariffPlanId() == cartItem1.getTariffPlanId()
                            && cartItem.getTerminalDeviceId() == cartItem1.getTerminalDeviceId()) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public Cart getCart(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        return cart;
    }

    private boolean lockConflict(Cart cart, CartItem cartItem){
        for (CartItem cartItem1: cart.getCartItems()){
            if (cartItem.getLockId() == cartItem1.getLockId()
                    && cartItem.getTerminalDeviceId() == cartItem1.getTerminalDeviceId()) {
                return true;
            }
        }
        return false;
    }

    private boolean optionConflict(Cart cart, CartItem cartItem){
        for (CartItem cartItem1: cart.getCartItems()){
            if (cartItem.getOptionId() == cartItem1.getOptionId()
                    && cartItem.getTerminalDeviceId() == cartItem1.getTerminalDeviceId()) {
                return true;
            }
        }
        return false;
    }
}
