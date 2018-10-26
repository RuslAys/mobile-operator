package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;

@Entity
@Table(name = "phone_numbers")
public class PhoneNumber extends AbstractPO {
    @Column(name = "number")
    private String number;

    @OneToOne(mappedBy = "phoneNumber")
    @JoinColumn(name = "terminal_device_id")
    private TerminalDevice terminalDevice;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TerminalDevice getTerminalDevice() {
        return terminalDevice;
    }

    public void setTerminalDevice(TerminalDevice terminalDevice) {
        this.terminalDevice = terminalDevice;
    }
}
