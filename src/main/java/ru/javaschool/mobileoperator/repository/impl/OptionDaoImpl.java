package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.Option;
import ru.javaschool.mobileoperator.domain.TariffPlan;
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
    public List<Option> getOptionsOnContractByNumber(Long number) {
        return currentSession()
                .createQuery("SELECT c.options FROM Contract c WHERE " +
                        "c.phoneNumber.number = :number")
                .setParameter("number", number).getResultList();
    }

    @Override
    public List<Option> getOptionsOnTariffPlan(TariffPlan tariffPlan) {
        return currentSession()
                .createQuery("SELECT o FROM Option o LEFT JOIN o.tariffPlans tp WHERE tp = :tp")
                .setParameter("tp", tariffPlan).getResultList();
    }

    @Override
    public List<Option> getOptionsNotIn(List<Long> ids) {
        return currentSession()
                .createQuery("SELECT o FROM Option o WHERE o.id NOT IN :ids")
                .setParameter("ids", ids).getResultList();
    }

    @Override
    public Option getFullOptionById(Long id) {
        return (Option) currentSession()
                .createQuery("SELECT o FROM Option o " +
                        "LEFT JOIN FETCH o.contracts LEFT JOIN FETCH o.tariffPlans " +
                        "WHERE o.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Option findOptionWithListsById(Long optionId) {
        return (Option) currentSession()
                .createQuery("SELECT o FROM Option o " +
                        "LEFT JOIN FETCH o.inclusiveOptions LEFT JOIN FETCH o.parentInclusive " +
                        "LEFT JOIN FETCH o.exclusiveOptions WHERE o.id = :id")
                .setParameter("id", optionId).getSingleResult();
    }
}
