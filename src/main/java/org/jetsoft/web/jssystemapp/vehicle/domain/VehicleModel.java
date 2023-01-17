package org.jetsoft.web.jssystemapp.vehicle.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

@Entity
@Table(schema = "data")
@Access(AccessType.FIELD)
public class VehicleModel extends AbstractEntityWithGeneratedId {

    private int manufacturerId;
    private String manufactureCode;
    private int maxPassengerCount;
    private int maxDistance;
    private int maxPilotCount;
    private int liftCapacity;
    private String modelName;

    public VehicleModel(int manufacturerId,
                        String manufacturerCode,
                        int maxPassengerCount,
                        int maxDistance,
                        int maxPilotCount,
                        int liftCapacity,
                        String modelName) {
        this.manufacturerId = manufacturerId;
        this.manufactureCode = manufacturerCode;
        this.maxPassengerCount = maxPassengerCount;
        this.maxDistance = maxDistance;
        this.maxPilotCount = maxPilotCount;
        this.liftCapacity = liftCapacity;
        this.modelName = modelName;
    }

    private VehicleModel() {}

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerCode() {
        return manufactureCode;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufactureCode = manufacturerCode;
    }

    public int getMaxPassengerCount() {
        return maxPassengerCount;
    }

    public void setMaxPassengerCount(int maxPassengerCount) {
        this.maxPassengerCount = maxPassengerCount;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public int getMaxPilotCount() {
        return maxPilotCount;
    }

    public void setMaxPilotCount(int maxPilotCount) {
        this.maxPilotCount = maxPilotCount;
    }

    public int getLiftCapacity() {
        return liftCapacity;
    }

    public void setLiftCapacity(int liftCapacity) {
        this.liftCapacity = liftCapacity;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
