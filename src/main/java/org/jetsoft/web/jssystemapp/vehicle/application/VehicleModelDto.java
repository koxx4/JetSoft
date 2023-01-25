package org.jetsoft.web.jssystemapp.vehicle.application;

public record VehicleModelDto(
        Long modelId,
        String modelName,
        int maxPassengerCount,
        int maxDistance,
        int maxPilotCount,
        int liftCapacity
) {}
