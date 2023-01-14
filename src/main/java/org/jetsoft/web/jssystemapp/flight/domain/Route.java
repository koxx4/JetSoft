package org.jetsoft.web.jssystemapp.flight.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.AbstractEntity;

@Entity
@Table(schema = "data")
@Access(AccessType.FIELD)
public class Route extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sourceCityId;
    private Long destinationCityId;
    private long distance;

    protected Route() {}

    public Route(Long sourceCityId, Long destinationCityId, long distance) {

        this.sourceCityId = sourceCityId;
        this.destinationCityId = destinationCityId;
        this.distance = distance;
    }

    public Long getSourceCityId() {
        return sourceCityId;
    }

    public Long getDestinationCityId() {
        return destinationCityId;
    }

    public long getDistance() {
        return distance;
    }
}
