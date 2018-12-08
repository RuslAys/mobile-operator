package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Bill;
import ru.javaschool.mobileoperator.domain.dto.BillDto;

import java.util.List;

public interface BillService extends GenericService<Bill, Long>  {
    List<BillDto> getBillsOnContract(Long contractId);
}
