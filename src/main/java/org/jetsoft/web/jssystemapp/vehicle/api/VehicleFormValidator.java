package org.jetsoft.web.jssystemapp.vehicle.api;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VehicleFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == VehicleForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
