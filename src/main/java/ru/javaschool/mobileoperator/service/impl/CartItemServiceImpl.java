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

//    @Autowired
//    private SaleService saleService;

    @Autowired
    private OptionService optionService;

    @Override
    public CartItem createItem(CartItemBuilder cartItemBuilder) {
        CartItem cartItem = new CartItem(cartItemBuilder);
        return cartItem;
    }

    @Override
    public CartItem updateItem(CartItem item, CartItemBuilder cartItemBuilder) {
        item.setCartItemOperationType(cartItemBuilder.getCartItemOperationType());
        item.setTariffPlanId(cartItemBuilder.getTariffPlanId());
        item.setOptionId(cartItemBuilder.getOptionId());
        item.setLockId(cartItemBuilder.getLockId());
        item.setCustomer(cartItemBuilder.getCustomer());
        item.setContractId(cartItemBuilder.getContractId());
        item.setPhoneNumberId(cartItemBuilder.getPhoneNumberId());
        item.setUser(cartItemBuilder.getUserDetails());
        return item;
    }

    @Override
    public void proceed(CartItem cartItem) {
        switch (cartItem.getCartItemOperationType()) {
//            case SALE:
////                saleService.saleContract(cartItem.getCustomer(), cartItem.getTariffPlanId(), cartItem.getPhoneNumberId());
//                break;
//            case SALE_TO_EXIST_CUSTOMER:
//                saleService.saleToExistCustomer(cartItem.getCustomerId(), cartItem.getTariffPlanId(), cartItem.getPhoneNumberId());
//                break;
            case ADD_OPTION:
                optionService.addOptionToContract(cartItem.getContractId(), cartItem.getOptionId());
                break;
            case REMOVE_OPTION:
                optionService.removeOptionFromContract(cartItem.getContractId(), cartItem.getOptionId());
                break;
            case LOCK:
                profileService.lockContract(cartItem.getContractId(), cartItem.getUser());
                break;
            case CHANGE_TARIFF:
                profileService.changeTariff(cartItem.getContractId(), cartItem.getTariffPlanId());
                break;
        }
    }
}