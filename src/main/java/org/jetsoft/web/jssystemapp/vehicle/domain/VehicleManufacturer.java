package org.jetsoft.web.jssystemapp.vehicle.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

@Entity
@Table(schema = "data")
@Access(AccessType.FIELD)
public class VehicleManufacturer extends AbstractEntityWithGeneratedId {

    private String companyName;

    public VehicleManufacturer(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
