package org.jetsoft.web.jssystemapp.location.domain;

import jakarta.persistence.*;

@Entity
@Table(schema = "data")
public enum Nationality {

    POLAND("Polska"),
    GERMANY("Niemcy"),
    SWEDEN("Szwecja");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    Nationality() {}

    Nationality(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
