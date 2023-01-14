package org.jetsoft.web.jssystemapp.core;

public abstract class AbstractEntity {

    private Long id;

    protected AbstractEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        id = value;
    }
}
