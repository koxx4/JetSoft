package org.jetsoft.web.jssystemapp.flight.api;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
class FlightFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz == FlightForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        FlightForm form = (FlightForm) target;

        validateRequiredFields(form, errors);
        validateArrivalBeforeDeparture(form, errors);
    }

    private void validateArrivalBeforeDeparture(FlightForm form, Errors errors) {

        var arrival = form.getPlannedArrival();
        var departure = form.getPlannedDeparture();

        if (arrival == null || departure == null) {
            return;
        }

        if (arrival.isBefore(form.getPlannedDeparture())) {

            errors.rejectValue("plannedArrival", "flight.arrival.beforeDeparture");
        }
    }

    private void validateAvailableSeats(FlightForm form, Errors errors) {
        var seats = form.getAvailablePassengersSeats();


    }

    private void validateSourceCity(FlightForm flightForm, Errors errors) {

    }

    private void validateDestinationCity(FlightForm flightForm, Errors errors) {

    }

    private void validateRequiredFields(FlightForm flightForm, Errors errors) {

        if (flightForm.getPlannedArrival() == null) {
            errors.rejectValue("plannedArrival", "field.required");
        }

        if (flightForm.getPlannedDeparture() == null) {
            errors.rejectValue("plannedDeparture", "field.required");
        }
    }
}
