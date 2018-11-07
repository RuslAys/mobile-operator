package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.*;
import ru.javaschool.mobileoperator.domain.enums.OperationType;

public interface CartItemService {
    CartItem createItem(int id, OperationType oType, TariffPlan tp, Option o,
                        Lock l, Customer c, TerminalDevice td, PhoneNumber phoneNumber);
    CartItem updateItem(CartItem item, OperationType operationType,  TariffPlan tp, Option o,
                        Lock l, Customer c, TerminalDevice td, PhoneNumber phoneNumber);
}
