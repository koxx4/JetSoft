package org.jetsoft.web.jssystemapp.vehicle.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;
import org.jetsoft.web.jssystemapp.core.JpaEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "data")
@Access(AccessType.FIELD)
public class Vehicle implements JpaEntity {

    @Id
    private Long id;
    private Long vehicleTypeId;
    private Long vehicleModelId;
    private LocalDate nextMaintenanceDate;
    private String friendlyName;
    private LocalDate rentDate;

    public Vehicle(Long id,
                   Long vehicleTypeId,
                   Long vehicleModelId,
                   LocalDate nextMaintenanceDate,
                   String friendlyName,
                   LocalDate rentDate) {
        this.id = id;
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

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long value) {

    }
}
