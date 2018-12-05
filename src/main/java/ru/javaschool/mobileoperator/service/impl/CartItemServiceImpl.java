package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.mobileoperator.domain.CartItem;
import ru.javaschool.mobileoperator.service.api.CartItemService;
import ru.javaschool.mobileoperator.service.api.OptionService;
import ru.javaschool.mobileoperator.service.api.ProfileService;
import ru.javaschool.mobileoperator.service.api.SaleService;
import ru.javaschool.mobileoperator.utils.CartItemBuilder;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private OptionService optionService;

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
//                saleService.saleContract(cartItem.getCustomer(), cartItem.getTariffPlanId(), cartItem.getPhoneNumberId());
                break;
            case SALE_TO_EXIST_PERSONAL_ACCOUNT:
                saleService.saleToExistPersonalAccount(cartItem.getPersonalAccountId(), cartItem.getTariffPlanId(), cartItem.getPhoneNumberId());
                break;
            case ADD_OPTION:
                optionService.addOptionToContract(cartItem.getTerminalDeviceId(), cartItem.getOptionId());
                break;
            case REMOVE_OPTION:
                optionService.removeOptionFromContract(cartItem.getTerminalDeviceId(), cartItem.getOptionId());
                break;
            case ADD_LOCK:
                break;
            case REMOVE_LOCK:
                break;
            case CHANGE_TARIFF:
                profileService.changeTariff(cartItem.getTerminalDeviceId(), cartItem.getTariffPlanId());
                break;
        }
    }
}