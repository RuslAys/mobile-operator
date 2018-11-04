package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.repository.api.OptionDao;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class OptionDaoImpl extends GenericDaoImpl<Option, Long>
        implements OptionDao {
    @Override
    public List<Option> getOptions() {
        return findAll();
    }

    @Override
    public List<Option> getOptions(List<Long> ids) {
        if(ids == null || ids.isEmpty()){
            return new ArrayList<>();
        }
        return currentSession()
                .createQuery("SELECT o FROM Option o where o.id IN :ids")
                .setParameter("ids", ids).getResultList();
    }

    @Override
    public List<Option> getOptionsByNumber(Long number) {
        return currentSession()
                .createQuery("SELECT td.options FROM TerminalDevice td WHERE " +
                        "td.phoneNumber.number = :number")
                .setParameter("number", number).getResultList();
    }
}