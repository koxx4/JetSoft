package org.jetsoft.web.jssystemapp.customer.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerRegistrationForm {

    @NotBlank(message = "${field.required}")
    private String firstName;
    @NotBlank(message = "${field.required}")
    private String lastName;
    @NotBlank(message = "${field.required}")
    @Size(min = 6, max = 40, message = "Hasło musi mieć od {min} do {max} znaków")
    private CharSequence password;
    @Email(message = "Podaj prawidłowy email")
    private String email;
    @NotBlank(message = "${field.required}")
    @Size(min = 12, max = 12, message = "Numer telefonu musi mieć 12 znaków: +[kierunkowy][numer]")
    private String phone;

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

    public CharSequence getPassword() {
        return password;
    }

    public void setPassword(CharSequence password) {
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
}
