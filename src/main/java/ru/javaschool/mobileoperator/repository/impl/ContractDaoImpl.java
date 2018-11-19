package ru.javaschool.mobileoperator.repository.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.Contract;
import ru.javaschool.mobileoperator.repository.api.ContractDao;

@Repository
public class ContractDaoImpl extends GenericDaoImpl<Contract, Long> implements ContractDao {
    @Override
    public Contract getContractWithOptionsById(long id) {
        String query = "SELECT c FROM Contract c LEFT JOIN FETCH c.options WHERE c.id = :id";
        return (Contract) currentSession()
                .createQuery(query)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Contract getContractWithOptionsByPhoneNumber(long number) {
        String query = "SELECT c FROM Contract c LEFT JOIN FETCH c.options WHERE c.phoneNumber.number = :number";
        return (Contract) currentSession()
                .createQuery(query)
                .setParameter("number", number)
                .getSingleResult();
    }
}
