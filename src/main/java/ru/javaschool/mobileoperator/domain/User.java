package ru.javaschool.mobileoperator.domain;

import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.Set;

@Entity()
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String username;
    @Column(length = 100)
    private String password;
    @Column
    private boolean active;
    @Column
    @ElementCollection(targetClass = UserRoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRoleEnum> roles;

    public User(){

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<UserRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleEnum> roles) {
        this.roles = roles;
    }
}
