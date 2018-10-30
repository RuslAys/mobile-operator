package ru.javaschool.mobileoperator.domain;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Authority() {
    }

    public Authority(User user, String authority) {
        this.user = user;
        this.authority = authority;
    }

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

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority='" + authority + "'}";
    }
}
