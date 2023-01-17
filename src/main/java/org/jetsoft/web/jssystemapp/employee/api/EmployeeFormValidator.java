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

    private void validateFormForExistingEmployee(Errors errors, EmployeeForm form) {

        String password = form.getPassword();

        if (StringUtils.length(password) >= 1) {
            validatePasswordLength(password, errors);
        }
    }

    private void validateFormForNewEmployee(Errors errors, EmployeeForm form) {

        String password = form.getPassword();

        if (isBlank(password)) {

            errors.rejectValue("password", "employee.newAccount.password.empty");
        }

        validatePasswordLength(password, errors);
    }

    private static void validateEmploymentDate(Errors errors, EmployeeForm form) {

        if (form.getEmploymentDate() != null) {

            if (isDateAfterToday(form.getEmploymentDate())) {

                errors.rejectValue("employmentDate", "employee.employmentDate.inFuture");
            }
        }
    }

    private static boolean isDateAfterToday(LocalDate date) {

        return date.isAfter(LocalDate.now());
    }

    private void validatePasswordLength(String password, Errors errors) {

        if (StringUtils.length(password) < 6) {

            errors.rejectValue("password", "employee.newAccount.password.tooShort");
        }
        else if (StringUtils.length(password) > 40) {

            errors.rejectValue("password", "employee.newAccount.password.tooLong");
        }
    }
}
