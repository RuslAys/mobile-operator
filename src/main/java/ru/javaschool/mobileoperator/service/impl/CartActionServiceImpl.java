package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.domain.enums.CartItemOperationType;
import ru.javaschool.mobileoperator.service.api.CartActionService;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

@Service("cartActionServiceImpl")
public class CartActionServiceImpl implements CartActionService {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public void changeTariffPlan(Cart cart, Long contractId, Long tariffId) {
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }
        CartItemBuilder builder = new CartItemBuilder.Builder(id, CartItemOperationType.CHANGE_TARIFF)
                .setTariffPlanId(tariffId)
                .setContractId(contractId)
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
    }

    @Override
    public void addOption(Cart cart, Long contractId, Long optionId) {
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }
        CartItemBuilder builder = new CartItemBuilder.Builder(id, CartItemOperationType.ADD_OPTION)
                .setOptionId(optionId)
                .setContractId(contractId)
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
    }

    @Override
    public void removeOption(Cart cart, Long contractId, Long optionId) {
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }
        CartItemBuilder builder = new CartItemBuilder.Builder(id, CartItemOperationType.REMOVE_OPTION)
                .setOptionId(optionId)
                .setContractId(contractId)
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
    }

    @Override
    public void lockContract(Cart cart, Long contractId, UserDetails userDetails) {
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }
        CartItemBuilder builder = new CartItemBuilder.Builder(id, CartItemOperationType.LOCK)
                .setContractId(contractId)
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
    }
}
