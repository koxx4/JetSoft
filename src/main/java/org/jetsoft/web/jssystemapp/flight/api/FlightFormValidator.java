package org.jetsoft.web.jssystemapp.flight.api;

import org.jetsoft.web.jssystemapp.location.application.RouteQueries;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
class FlightFormValidator implements Validator {

    public static final String FIELD_REQUIRED_MSG = "field.required";

    private final RouteQueries routeQueries;
    private final VehicleQueries vehicleQueries;

    @Autowired
    FlightFormValidator(RouteQueries routeQueries, VehicleQueries vehicleQueries) {

        this.routeQueries = routeQueries;
        this.vehicleQueries = vehicleQueries;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz == FlightForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        Assert.notNull(target, "target cannot be null");

        FlightForm form = (FlightForm) target;

        validateRequiredFields(form, errors);
        validateArrivalBeforeDeparture(form, errors);
        validateAvailableSeats(form, errors);
        validatePilots(form,errors);
        validateRoute(form, errors);
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

    private void validatePilots(FlightForm form, Errors errors) {

        Long vehicleId = form.getVehicleId();

        if (vehicleId == null) {
            return;
        }

        long maxPilotCount = vehicleQueries.getMaxPilotCount(vehicleId);

        if (form.getMinPilotCount() > maxPilotCount) {
            errors.rejectValue("minPilotCount", "flight.passengers.pilotCount.tooBig");
        }
        if (form.getMinPilotCount() <= 0 ) {
            errors.rejectValue("minPilotCount", "flight.passengers.pilotCount.negative");
        }
    }

    private void validateAvailableSeats(FlightForm form, Errors errors) {

        Long vehicleId = form.getVehicleId();

        if (vehicleId == null) {
            return;
        }

        long maxPassengerCount = vehicleQueries.getMaxPassengerCount(vehicleId);

        if (form.getAvailablePassengersSeats() > maxPassengerCount) {
            errors.rejectValue("availablePassengersSeats", "flight.passengers.count.tooBig");
        }
        if (form.getAvailablePassengersSeats() <= 0 ) {
            errors.rejectValue("availablePassengersSeats", "flight.passengers.count.negative");
        }
    }

    private void validateRoute(FlightForm flightForm, Errors errors) {

        Long vehicleId = flightForm.getVehicleId();
        Long routeId = flightForm.getRouteId();

        if (vehicleId == null || routeId == null) {
            return;
        }

        long maxDistance = vehicleQueries.getMaxDistance(vehicleId);
        long routeDistance = routeQueries.getRouteDistanceByRouteId(routeId);

        if (routeDistance > maxDistance) {
            errors.rejectValue("routeId", "flight.route.distance.tooLong");
        }
    }

    private void validateRequiredFields(FlightForm flightForm, Errors errors) {

        if (flightForm.getPlannedArrival() == null) {
            errors.rejectValue("plannedArrival", FIELD_REQUIRED_MSG);
        }

        if (flightForm.getPlannedDeparture() == null) {
            errors.rejectValue("plannedDeparture", FIELD_REQUIRED_MSG);
        }

        if (flightForm.getVehicleId() == null) {
            errors.rejectValue("vehicleId", FIELD_REQUIRED_MSG);
        }

        if (flightForm.getRouteId() == null) {
            errors.rejectValue("routeId", FIELD_REQUIRED_MSG);
        }
    }
}
