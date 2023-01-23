package org.jetsoft.web.jssystemapp.customer.api;

import jakarta.validation.constraints.Size;

public class CustomerFilterForm {
    @Size(max = 50, message = "Ciąg znaków jest za długi!")
    private String firstName;
    @Size(max = 50, message = "Ciąg znaków jest za długi!")
    private String lastName;
    @Size(max = 12, message = "Numer telefonu może mieć maksymalnie 12 znaków!")
    private String phone;
    private String email;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
