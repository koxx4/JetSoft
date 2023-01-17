package org.jetsoft.web.jssystemapp.vehicle.application;

import java.time.LocalDate;

public class VehicleRowDto {

    private Long vehicleId;
    private String vehicleType;
    private String vehicleModel;
    private LocalDate nextMaintenanceDate;
    private String friendlyName;
    private LocalDate rentDate;
    private int maxPassengerCount;
    private int maxDistance;
    private int maxPilotCount;
    private int liftCapacity;

    public VehicleRowDto(Long vehicleId,
                         String vehicleType,
                         String vehicleModel,
                         LocalDate nextMaintenanceDate,
                         String friendlyName,
                         LocalDate rentDate,
                         int maxPassengerCount,
                         int maxDistance,
                         int maxPilotCount,
                         int maxCapacity) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.friendlyName = friendlyName;
        this.rentDate = rentDate;
        this.maxPassengerCount = maxPassengerCount;
        this.maxDistance = maxDistance;
        this.maxPilotCount = maxPilotCount;
        this.liftCapacity = maxCapacity;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public LocalDate getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public int getMaxPassengerCount() {
        return maxPassengerCount;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public int getMaxPilotCount() {
        return maxPilotCount;
    }

    public int getLiftCapacity() {
        return liftCapacity;
    }
}
