package org.jetsoft.web.jssystemapp.vehicle.application;

import java.time.LocalDate;

public class VehicleRowDto {

    private Long vehicleId;
    private int vehicleTypeId;
    private int vehicleModelId;
    private LocalDate nextMaintenanceDate;
    private String friendlyName;
    private LocalDate rentDate;

    public VehicleRowDto(Long vehicleId,
                         int vehicleTypeId,
                         int vehicleModelId,
                         LocalDate nextMaintenanceDate,
                         String friendlyName,
                         LocalDate rentDate) {
        this.vehicleId = vehicleId;
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleModelId = vehicleModelId;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.friendlyName = friendlyName;
        this.rentDate = rentDate;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public int getVehicleModelId() {
        return vehicleModelId;
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
}
