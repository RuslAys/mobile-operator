package ru.javaschool.mobileoperator.service.impl;

import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.*;
import ru.javaschool.mobileoperator.domain.enums.OperationType;
import ru.javaschool.mobileoperator.service.api.CartItemService;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {
    @Override
    public CartItem createItem(int id, OperationType oType, TariffPlan tp, Option o, Lock l, Customer c, TerminalDevice td) {
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setOperationType(oType);
        cartItem.setTariffPlan(tp);
        cartItem.setOption(o);
        cartItem.setLock(l);
        cartItem.setCustomer(c);
        cartItem.setTerminalDevice(td);
        return cartItem;
    }

    @Override
    public CartItem updateItem(CartItem item, OperationType oType, TariffPlan tp, Option o, Lock l, Customer c, TerminalDevice td) {
        item.setOperationType(oType);
        item.setTariffPlan(tp);
        item.setOption(o);
        item.setLock(l);
        item.setCustomer(c);
        item.setTerminalDevice(td);
        return item;
    }
}
