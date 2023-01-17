package org.jetsoft.web.jssystemapp.vehicle.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jetsoft.web.jssystemapp.core.AbstractEntity;

import java.time.LocalDate;

@Entity
@Table(schema = "data")
@Access(AccessType.FIELD)
public class Vehicle extends AbstractEntity {

    private Long vehicleTypeId;
    private Long vehicleModelId;
    private LocalDate nextMaintenanceDate;
    private String friendlyName;
    private LocalDate rentDate;

    public Vehicle(Long vehicleTypeId,
                   Long vehicleModelId,
                   LocalDate nextMaintenanceDate,
                   String friendlyName,
                   LocalDate rentDate) {
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleModelId = vehicleModelId;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.friendlyName = friendlyName;
        this.rentDate = rentDate;
    }

    private Vehicle() {}

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
}
