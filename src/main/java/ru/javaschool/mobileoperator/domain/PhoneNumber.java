package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;

@Entity
@Table(name = "phone_numbers")
public class PhoneNumber extends AbstractPO {
    @Column(name = "number",unique = true, nullable = false)
    private Long number;

    @OneToOne(mappedBy = "phoneNumber")
    private TerminalDevice terminalDevice;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public TerminalDevice getTerminalDevice() {
        return terminalDevice;
    }

    public void setTerminalDevice(TerminalDevice terminalDevice) {
        this.terminalDevice = terminalDevice;
    }

    @Override
    public String toString() {
        return Long.toString(number);
    }
}
