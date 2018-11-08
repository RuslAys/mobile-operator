package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.*;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.api.SaleService;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SaleService saleService;

    @Override
    public CartItem createItem(int id, OperationType oType, TariffPlan tp,
                               Option o, Lock l, Customer c, TerminalDevice td, PhoneNumber phoneNumber) {
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setOperationType(oType);
        cartItem.setTariffPlan(tp);
        cartItem.setOption(o);
        cartItem.setLock(l);
        cartItem.setCustomer(c);
        cartItem.setTerminalDevice(td);
        cartItem.setPhoneNumber(phoneNumber);
        return cartItem;
    }

    @Override
    public CartItem updateItem(CartItem item, OperationType oType, TariffPlan tp,
                               Option o, Lock l, Customer c, TerminalDevice td, PhoneNumber phoneNumber) {
        item.setOperationType(oType);
        item.setTariffPlan(tp);
        item.setOption(o);
        item.setLock(l);
        item.setCustomer(c);
        item.setTerminalDevice(td);
        item.setPhoneNumber(phoneNumber);
        return item;
    }

    @Override
    public void proceed(CartItem cartItem) {
        switch (cartItem.getOperationType()){
            case SALE:
                saleService.saleContract(cartItem.getCustomer(), cartItem.getTariffPlan(), cartItem.getPhoneNumber());
                break;
            case ADD_OPTION:
                break;
            case REMOVE_OPTION:
                break;
            case ADD_LOCK:
                break;
            case REMOVE_LOCK:
                break;
            case CHANGE_TARIFF:
                break;
        }
    }
}