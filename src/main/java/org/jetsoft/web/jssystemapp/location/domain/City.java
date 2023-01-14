package org.jetsoft.web.jssystemapp.location.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.AbstractEntity;

@Entity
@Table(schema = "data")
public class City extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nationalityId;
    private String name;

    protected City() {}

    public City(Long nationalityId, String name) {
        this.nationalityId = nationalityId;
        this.name = name;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public String getName() {
        return name;
    }
}
