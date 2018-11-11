package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.PersonalAccount;

public interface PersonalAccountDao extends GenericDao<PersonalAccount, Long> {
    /**
     * Method to find personal account with terminal devices
     * @param id personal account id
     * @return personal account
     */
    PersonalAccount getPersonalAccountWithTerminalDevices(Long id);
}
