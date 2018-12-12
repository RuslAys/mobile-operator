package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.Cart;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;
import ru.javaschool.mobileoperator.domain.enums.CartItemOperationType;
import ru.javaschool.mobileoperator.service.api.CartActionService;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.CartService;
import ru.javaschool.mobileoperator.service.api.ContractService;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.api.TariffService;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

@Service("cartActionServiceImpl")
public class CartActionServiceImpl implements CartActionService {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private ContractService contractService;

    @Override
    public void changeTariffPlan(Cart cart, Long contractId, Long tariffId) {
        TariffPlan plan = tariffService.find(tariffId);
        Contract contract = contractService.find(contractId);
        if(plan != null){
            int id = getId(cart);
            CartItemBuilder builder = new CartItemBuilder.Builder(id, CartItemOperationType.CHANGE_TARIFF)
                    .setTariffPlanId(tariffId)
                    .setContractId(contractId)
                    .setTitle(plan.getName() + " on " + contract.getPhoneNumber().getNumber())
                    .build();
            CartItem item = cartItemService.createItem(builder);
            cartService.addItem(cart, item);
        }
    }

    @Override
    public void addOption(Cart cart, Long contractId, Long optionId) {
        Option option = optionService.find(optionId);
        Contract contract = contractService.find(contractId);
        if(option != null){
            int id = getId(cart);
            CartItemBuilder builder = new CartItemBuilder.Builder(id, CartItemOperationType.ADD_OPTION)
                    .setOptionId(optionId)
                    .setContractId(contractId)
                    .setTitle(option.getName() + " on " + contract.getPhoneNumber().getNumber())
                    .build();
            CartItem item = cartItemService.createItem(builder);
            cartService.addItem(cart, item);
        }
    }

    @Override
    public void removeOption(Cart cart, Long contractId, Long optionId) {
        Option option = optionService.find(optionId);
        Contract contract = contractService.find(contractId);
        if(option != null){
            int id = getId(cart);
            CartItemBuilder builder = new CartItemBuilder.Builder(id, CartItemOperationType.REMOVE_OPTION)
                    .setOptionId(optionId)
                    .setContractId(contractId)
                    .setTitle(option.getName() + " on " + contract.getPhoneNumber().getNumber())
                    .build();
            CartItem item = cartItemService.createItem(builder);
            cartService.addItem(cart, item);
        }
    }

    @Override
    public void lockContract(Cart cart, Long contractId, UserDetails userDetails) {
        Contract contract = contractService.find(contractId);
        String type;
        if(contract.isLocked()){
            type = "Unlock";
        }else {
            type = "Lock";
        }
        int id = getId(cart);
        CartItemBuilder builder = new CartItemBuilder.Builder(id, CartItemOperationType.LOCK)
                .setContractId(contractId)
                .setUserDetails(userDetails)
                .setTitle(type + " " + contract.getPhoneNumber().getNumber())
                .build();
        CartItem item = cartItemService.createItem(builder);
        cartService.addItem(cart, item);
    }

    private int getId(Cart cart){
        int id = 0;
        if(!cart.getCartItems().isEmpty()){
            id = cart.getCartItems().get(cart.getCartItems().size()-1).getId()+1;
        }
        return id;
    }
}
