package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.Bill;

import java.util.List;

public interface BillDao extends GenericDao<Bill, Long> {
    List<Bill> getBillsOnContract(Long contractId);
}
