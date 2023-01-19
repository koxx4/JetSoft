package org.jetsoft.web.jssystemapp.customer.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

import java.time.LocalDateTime;

@Table(schema = "ppd")
@Entity
@Access(AccessType.FIELD)
public class Customer extends AbstractEntityWithGeneratedId {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime registrationDate;

    private Customer() {}

    public Customer(String firstName, String lastName, String password, String email, String phone, LocalDateTime registrationDate) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.registrationDate = registrationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
