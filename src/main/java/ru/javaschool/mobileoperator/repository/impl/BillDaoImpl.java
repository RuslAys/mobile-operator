package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.Bill;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.repository.api.BillDao;

import java.util.List;

@Repository
public class BillDaoImpl extends GenericDaoImpl<Bill, Long> implements BillDao {

    @Override
    public List<Bill> getBillsOnContract(Long contractId) {
        String query = "SELECT b FROM Bill b WHERE b.contract.id = :contractId";
        return currentSession()
                .createQuery(query)
                .setParameter("contractId", contractId)
                .getResultList();
    }
}
