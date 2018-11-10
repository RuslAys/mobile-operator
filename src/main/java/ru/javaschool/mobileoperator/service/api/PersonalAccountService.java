package ru.javaschool.mobileoperator.service.api;

import ru.javaschool.mobileoperator.domain.PersonalAccount;

public interface PersonalAccountService extends GenericService<PersonalAccount, Long> {
    PersonalAccount getPersonalAccountWithTerminalDevices(Long id);
}
