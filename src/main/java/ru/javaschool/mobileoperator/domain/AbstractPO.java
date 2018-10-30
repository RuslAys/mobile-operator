package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;

@MappedSuperclass
public class AbstractPO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    protected Long id;

    @Version
    protected Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "AbstractPO{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }
}
