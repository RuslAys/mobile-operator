package ru.javaschool.mobileoperator.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TERMINAL_DEVICE")
public class TerminalDevice extends AbstractPO {
    @ManyToOne
    @JoinColumn(name = "personal_account_id")
    private PersonalAccount personalAccount;
}
