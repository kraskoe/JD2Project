package com.pvt.app.pojos;

import com.pvt.app.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yauheni Krasko on 17.11.2017.
 */

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@ToString(exclude = "orders")
@Table(name = "USERS")
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    @Pattern(regexp="^[A-Z]+[a-z]+$", message="Username must be alphanumeric with no spaces and first capital")
    private String name;

    @Column(name = "SURNAME")
    @Pattern(regexp="^[A-Z]+[a-z]+$", message="Username must be alphanumeric with no spaces and first capital")
    private String surname;

    @Column(name = "PHONE")
    @Pattern(regexp="^\\+?375[\\- ]?\\(?\\d{2}\\)?[\\d\\- ]{7,10}$", message="Phone must be in international format, beginning from 375")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL", length = 30, unique = true, nullable = false)
    @Pattern(regexp="^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$", message="E-mail must be correct")
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @Max(value = 30, message = "Max password length should be less then 30 characters")
    @Min(value = 6, message = "Min password length should be greater then 6 characters")
    private String password;

    @Column(name="TYPE", length=10, nullable = false)
    private String type = UserType.USER.getType();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public User(String name, String surname, String phone, String address, String email, String password){
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return surname != null ? surname.equals(user.surname) : user.surname == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

//    @Override
//    public String toString() {
//        return "User : id: " + id + " Name: " + name + " Surname: " + surname;
//    }
}
