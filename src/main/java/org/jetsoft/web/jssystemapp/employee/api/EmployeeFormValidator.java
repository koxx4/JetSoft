package org.jetsoft.web.jssystemapp.employee.api;

import org.jetsoft.web.jssystemapp.employee.application.EmployeeAccountQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDate;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Component
class EmployeeFormValidator implements Validator {

    private final EmployeeAccountQueries accountQueries;

    @Autowired
    EmployeeFormValidator(EmployeeAccountQueries accountQueries) {

        this.accountQueries = accountQueries;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == EmployeeForm.class;
    }

    /**
     * If the employee ID is not found in the database, then validate the form for a new employee, otherwise validate the
     * form for an existing employee
     *
     * @param target The object to be validated.
     * @param errors This is the object that will be used to report any validation errors.
     */
    @Override
    public void validate(Object target, Errors errors) {

        EmployeeForm form = (EmployeeForm) target;

        if (!accountQueries.exists(form.getEmployeeId())) {

            validateFormForNewEmployee(errors, form);
        }
        else {

            validateFormForExistingEmployee(errors, form);
        }

        validateEmploymentDate(errors, form);
    }

    /**
     * If the password field is not empty, validate the password length
     *
     * @param errors This is the object that will hold the errors.
     * @param form The form object that is being validated.
     */
    private void validateFormForExistingEmployee(Errors errors, EmployeeForm form) {

        String password = form.getPassword();

        if (StringUtils.length(password) >= 1) {
            validatePasswordLength(password, errors);
        }
    }

    /**
     * If the password is blank, reject the password field with the error code `employee.newAccount.password.empty`.
     *
     * @param errors This is the object that will hold the errors.
     * @param form The form object that is being validated.
     */
    private void validateFormForNewEmployee(Errors errors, EmployeeForm form) {

        String password = form.getPassword();

        if (isBlank(password)) {

            errors.rejectValue("password", "employee.newAccount.password.empty");
        }

        validatePasswordLength(password, errors);
    }

    /**
     * If the employment date is not null, and the employment date is after today, then reject the value of the employment
     * date field with the error code "employee.employmentDate.inFuture"
     *
     * @param errors The Errors object that will be used to report any validation errors.
     * @param form The form object that is being validated.
     */
    private static void validateEmploymentDate(Errors errors, EmployeeForm form) {

        if (form.getEmploymentDate() != null) {

            if (isDateAfterToday(form.getEmploymentDate())) {

                errors.rejectValue("employmentDate", "employee.employmentDate.inFuture");
            }
        }
    }

    /**
     * If the date is after today, return true
     *
     * @param date The date to check
     * @return A boolean value.
     */
    private static boolean isDateAfterToday(LocalDate date) {

        return date.isAfter(LocalDate.now());
    }

    /**
     * If the password is less than 6 characters, reject the password field with the error code
     * "employee.newAccount.password.tooShort". If the password is greater than 40 characters, reject the password field
     * with the error code "employee.newAccount.password.tooLong"
     *
     * @param password The name of the field that is being validated.
     * @param errors The Errors object that will be used to report any validation errors that occur.
     */
    private void validatePasswordLength(String password, Errors errors) {

        if (StringUtils.length(password) < 6) {

            errors.rejectValue("password", "employee.newAccount.password.tooShort");
        }
        else if (StringUtils.length(password) > 40) {

            errors.rejectValue("password", "employee.newAccount.password.tooLong");
        }
    }
}
