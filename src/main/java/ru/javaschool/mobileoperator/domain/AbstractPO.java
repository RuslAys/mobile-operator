package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;

@MappedSuperclass
public class AbstractPO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
