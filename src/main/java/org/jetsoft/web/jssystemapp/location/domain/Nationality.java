package org.jetsoft.web.jssystemapp.location.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

@Entity
@Table(schema = "data")
public class Nationality extends AbstractEntityWithGeneratedId {

    private String name;

    protected Nationality() {}

    Nationality(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
