package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.Bill;
import ru.javaschool.mobileoperator.domain.dto.BillDto;

import java.util.List;

/**
 * Service to work with bills
 */
public interface BillService extends GenericService<Bill, Long>  {
    /**
     * Method to get all bills on contract by contract id
     * @param contractId contract id
     * @return list with bills dto
     */
    List<BillDto> getBillsOnContract(Long contractId);
}
