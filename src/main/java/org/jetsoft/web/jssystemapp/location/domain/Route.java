package org.jetsoft.web.jssystemapp.location.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

@Entity
@Table(schema = "data")
@Access(AccessType.FIELD)
public class Route extends AbstractEntityWithGeneratedId {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "source_city_id")
    private City sourceCity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "destination_city_id")
    private City destinationCity;

    private Long distance;

    public Route(City sourceCity, City destinationCity, Long distance) {

        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.distance = distance;
    }

    private Route() {}

    public City getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(City sourceCity) {
        this.sourceCity = sourceCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }
}
