package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.Lock;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.repository.api.LockDao;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class LockDaoImpl extends GenericDaoImpl<Lock, Long>
        implements LockDao {

    @Override
    public List<Lock> getLockNotIn(List<Long> ids) {
        String query = "SELECT l FROM Lock l WHERE l.id NOT IN :lockIds";
        return currentSession()
                .createQuery(query)
                .setParameter("lockIds", ids)
                .getResultList();
    }

    @Override
    public List<Lock> findBy(List<Long> lockIds) {
        String query = "SELECT l FROM Lock l WHERE l.id IN :lockIds";
        return currentSession()
                .createQuery(query)
                .setParameter("lockIds", lockIds)
                .getResultList();
    }
}
