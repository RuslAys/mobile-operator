package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.PersonalAccount;

public interface PersonalAccountDao extends GenericDao<PersonalAccount, Long> {
    PersonalAccount getPersonalAccountWithTerminalDevices(Long id);
}
