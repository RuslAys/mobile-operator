package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.PersonalAccount;

public interface PersonalAccountService extends GenericService<PersonalAccount, Long> {
    /**
     * Method to find personal account with relations terminal devices
     * @param id personal account id
     * @return personal account
     */
    PersonalAccount getPersonalAccountWithTerminalDevices(Long id);
}
