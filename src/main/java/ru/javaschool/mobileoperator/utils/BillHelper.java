package ru.javaschool.mobileoperator.utils;

import ru.javaschool.mobileoperator.domain.Bill;
import ru.javaschool.mobileoperator.domain.Contract;

import java.util.Date;

public class BillHelper {
    public static Bill makeBill(Contract contract, int balance, int difference, String operation){
        Bill bill = new Bill();
        bill.setContract(contract);
        bill.setBalance(balance);
        bill.setDifference(difference);
        bill.setDate(new Date());
        bill.setOperation(operation);
        return bill;
    }
}
