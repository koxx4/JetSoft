package org.jetsoft.web.jssystemapp.location.domain;

import jakarta.persistence.*;

@Entity
@Table(schema = "data")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nationalityId;
    private String name;

    protected City() {
    }

    public City(Long nationalityId, String name) {
        this.nationalityId = nationalityId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public String getName() {
        return name;
    }
}
