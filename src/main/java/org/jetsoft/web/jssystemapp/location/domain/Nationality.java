package org.jetsoft.web.jssystemapp.location.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.AbstractEntity;

@Entity
@Table(schema = "data")
public class Nationality extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    protected Nationality() {}

    Nationality(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
