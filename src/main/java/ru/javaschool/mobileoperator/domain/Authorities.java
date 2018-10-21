package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITIES")
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "AUTHORITY")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
