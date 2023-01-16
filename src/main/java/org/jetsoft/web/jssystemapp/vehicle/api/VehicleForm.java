package org.jetsoft.web.jssystemapp.vehicle.api;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class VehicleForm{

    private Long id;
    private int vehicleTypeId;
    private int vehicleModelId;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate nextMaintenanceDate;
    private String friendlyName;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate rentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public int getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(int vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public LocalDate getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }

    public void setNextMaintenanceDate(LocalDate nextMaintenanceDate) {
        this.nextMaintenanceDate = nextMaintenanceDate;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }
}
