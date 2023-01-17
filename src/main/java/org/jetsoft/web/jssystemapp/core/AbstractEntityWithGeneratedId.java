package org.jetsoft.web.jssystemapp.core;

import jakarta.persistence.*;

@Access(AccessType.FIELD)
@MappedSuperclass
public abstract class AbstractEntityWithGeneratedId implements JpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected AbstractEntityWithGeneratedId() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long value) {
        id = value;
    }
}
