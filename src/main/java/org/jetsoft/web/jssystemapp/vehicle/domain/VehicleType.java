package org.jetsoft.web.jssystemapp.vehicle.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

@Entity
@Table(schema = "data")
@Access(AccessType.FIELD)
public class VehicleType extends AbstractEntityWithGeneratedId {

    private String typeName;

    public VehicleType(String typeName) {
        this.typeName = typeName;
    }

    private VehicleType() {}

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
