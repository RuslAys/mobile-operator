package ru.javaschool.mobileoperator.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.LockService;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.api.SaleService;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

    private final Logger logger = LogManager.getLogger(CartItemServiceImpl.class);

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private LockService lockService;

    @Override
    public CartItem createItem(CartItemBuilder cartItemBuilder) {
        CartItem cartItem = new CartItem(cartItemBuilder);
        return cartItem;
    }

    @Override
    public CartItem updateItem(CartItem item, CartItemBuilder cartItemBuilder) {
        item.setOperationType(cartItemBuilder.getOperationType());
        item.setTariffPlanId(cartItemBuilder.getTariffPlanId());
        item.setOptionId(cartItemBuilder.getOptionId());
        item.setLockId(cartItemBuilder.getLockId());
        item.setCustomer(cartItemBuilder.getCustomer());
        item.setTerminalDeviceId(cartItemBuilder.getTerminalDeviceId());
        item.setPhoneNumberId(cartItemBuilder.getPhoneNumberId());
        item.setUser(cartItemBuilder.getUserDetails());
        return item;
    }

    @Override
    public void proceed(CartItem cartItem) {
        switch (cartItem.getOperationType()){
            case SALE:
                saleService.saleContract(cartItem.getCustomer(), cartItem.getTariffPlanId(), cartItem.getPhoneNumberId());
                break;
            case ADD_OPTION:
                optionService.addOptionToTerminalDevice(cartItem.getTerminalDeviceId(), cartItem.getOptionId());
                break;
            case REMOVE_OPTION:
                optionService.removeOptionFromTerminalDevice(cartItem.getTerminalDeviceId(), cartItem.getOptionId());
                break;
            case ADD_LOCK:
                lockService.addLock(cartItem.getUser(), cartItem.getTerminalDeviceId(), cartItem.getLockId());
                break;
            case REMOVE_LOCK:
                lockService.removeLock(cartItem.getUser(), cartItem.getTerminalDeviceId(), cartItem.getLockId());
                break;
            case CHANGE_TARIFF:
                profileService.changeTariff(cartItem.getTerminalDeviceId(), cartItem.getTariffPlanId());
                break;
        }
    }
}