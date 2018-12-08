package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.domain.Bill;
import ru.javaschool.mobileoperator.domain.dto.BillDto;
import ru.javaschool.mobileoperator.repository.api.BillDao;
import ru.javaschool.mobileoperator.repository.api.ContractDao;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.service.api.BillService;
import ru.javaschool.mobileoperator.utils.DtoConverter;

import java.util.ArrayList;
import java.util.List;

@Service("billService")
public class BillServiceImpl extends GenericServiceImpl<Bill, Long> implements BillService {

    @Autowired
    private BillDao billDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    public BillServiceImpl(@Qualifier("billDaoImpl") GenericDao<Bill, Long> genericDao){
        super(genericDao);
        this.billDao = (BillDao) genericDao;
    }

    public BillServiceImpl() {
    }

    @Override
    @Transactional(readOnly = true)
    public List<BillDto> getBillsOnContract(Long contractId) {
        List<Bill> bills = billDao.getBillsOnContract(contractId);
        List<BillDto> dtos = new ArrayList<>();
        bills.forEach(bill -> dtos.add(DtoConverter.billToBillDtoWithoutContract(bill)));
        return dtos;
    }
}
