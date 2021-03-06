package ru.javaschool.mobileoperator.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;

import javax.servlet.http.HttpSession;

/**
 * Class with util methods to user cart
 */
@Component
@Scope("singleton")
public class CartHelper {

    /**
     * Method to check exists cart item in cart or not
     * @param cart cart
     * @param cartItem cart item
     * @return true if exist false if not
     */
    public boolean alreadyExist(Cart cart, CartItem cartItem){
        switch (cartItem.getCartItemOperationType()){
            case LOCK:
                for (CartItem cartItem1: cart.getCartItems()){
                    if (cartItem.getContractId() == cartItem1.getContractId()) {
                        return true;
                    }
                }
                break;
            case ADD_OPTION:
                return optionConflict(cart, cartItem);
            case REMOVE_OPTION:
                return optionConflict(cart, cartItem);
            case CHANGE_TARIFF:
                for (CartItem cartItem1: cart.getCartItems()){
                    if (cartItem.getTariffPlanId() == cartItem1.getTariffPlanId()
                            && cartItem.getContractId() == cartItem1.getContractId()) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    /**
     * Method to check lock conflicts in cart`s cart items
     * @param cart cart
     * @param cartItem cart item
     * @return true if exist conflicts false if not
     */
    private boolean lockConflict(Cart cart, CartItem cartItem){
        for (CartItem cartItem1: cart.getCartItems()){
            if (cartItem.getLockId() == cartItem1.getLockId()
                    && cartItem.getContractId() == cartItem1.getContractId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check option conflicts in cart`s cart items
     * @param cart cart
     * @param cartItem cart item
     * @return true if exist conflicts false if not
     */
    private boolean optionConflict(Cart cart, CartItem cartItem){
        for (CartItem cartItem1: cart.getCartItems()){
            if (cartItem.getOptionId() == cartItem1.getOptionId()
                    && cartItem.getContractId() == cartItem1.getContractId()) {
                return true;
            }
        }
        return false;
    }
}