package org.jetsoft.web.jssystemapp.vehicle.api;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class VehicleForm{

    private Long id;
    private Long vehicleTypeId;
    private Long vehicleModelId;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate nextMaintenanceDate;
    private String friendlyName;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate rentDate;
    private String typeName;
    private String modelName;
    private int maxPassengersCount;
    private int maxPilotCount;
    private int maxLift;
    private int maxDistance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Long getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(Long vehicleModelId) {
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

    public int getMaxPassengersCount() {
        return maxPassengersCount;
    }

    public void setMaxPassengersCount(int maxPassengersCount) {
        this.maxPassengersCount = maxPassengersCount;
    }

    public int getMaxPilotCount() {
        return maxPilotCount;
    }

    public void setMaxPilotCount(int maxPilotCount) {
        this.maxPilotCount = maxPilotCount;
    }

    public int getMaxLift() {
        return maxLift;
    }

    public void setMaxLift(int maxLift) {
        this.maxLift = maxLift;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
