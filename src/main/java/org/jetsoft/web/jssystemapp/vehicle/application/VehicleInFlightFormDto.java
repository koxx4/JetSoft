package org.jetsoft.web.jssystemapp.vehicle.application;

public record VehicleInFlightFormDto(
        Long vehicleId,
        String vehicleType,
        String vehicleModel,
        String friendlyName,
        long maxPassengers,
        long maxPilots,
        long maxDistance) {}
