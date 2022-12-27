package com.jobinjose.talize.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class UserModel {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String encTocken;
    private String password;
    private String emailStatus;

    public UserModel() {
    }

    public UserModel(int id, String name, String email, String encTocken, String password, String emailStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.encTocken = encTocken;
        this.password = password;
        this.emailStatus = emailStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncTocken() {
        return encTocken;
    }

    public void setEncTocken(String encTocken) {
        this.encTocken = encTocken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }
}
