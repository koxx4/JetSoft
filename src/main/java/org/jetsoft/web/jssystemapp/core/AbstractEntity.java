package org.jetsoft.web.jssystemapp.core;

import jakarta.persistence.*;

@Access(AccessType.FIELD)
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected AbstractEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        id = value;
    }
}
