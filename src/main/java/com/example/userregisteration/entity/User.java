package com.example.userregisteration.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name="first_name")
    private String firstName;

    @Column(nullable = false, name="last_name")
    private String lastName;

    @Column(nullable = false, name="email", unique = true)
    private String email;

    @Column(nullable = false, name="gender")
    private String gender;

    // Getters
    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getGender(){
        return this.gender;
    }

    // Setters
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setGender(String gender){
        this.gender = gender;
    }
}
