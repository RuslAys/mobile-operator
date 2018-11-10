package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.PersonalAccount;
import ru.javaschool.mobileoperator.domain.TerminalDevice;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.PersonalAccountDao;

@Repository
public class PersonalAccountDaoImpl extends GenericDaoImpl<PersonalAccount, Long> implements PersonalAccountDao {
    @Override
    public PersonalAccount getPersonalAccountWithTerminalDevices(Long id) {
        String query = "SELECT pa FROM PersonalAccount pa LEFT JOIN FETCH pa.terminalDevices WHERE pa.id = :id";
        return (PersonalAccount) currentSession()
                .createQuery(query)
                .setParameter("id", id)
                .getSingleResult();
    }
}
