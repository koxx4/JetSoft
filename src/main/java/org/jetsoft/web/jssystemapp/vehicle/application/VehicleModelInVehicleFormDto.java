package org.jetsoft.web.jssystemapp.vehicle.application;

public record VehicleModelInVehicleFormDto(
        Long vehicleId,
        String vehicleType,
        String vehicleModel,
        int vehicleMaxPassengerCount,
        int vehicleMaxDistance,
        int vehicleMaxPilotCount,
        int vehicleLiftCapacity
) {}
