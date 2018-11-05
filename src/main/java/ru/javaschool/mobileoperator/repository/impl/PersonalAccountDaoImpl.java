package ru.javaschool.mobileoperator.repository.impl;

import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.PersonalAccount;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.PersonalAccountDao;

@Repository
public class PersonalAccountDaoImpl extends GenericDaoImpl<PersonalAccount, Long> implements PersonalAccountDao {
}
