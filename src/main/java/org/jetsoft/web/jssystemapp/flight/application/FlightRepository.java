package org.jetsoft.web.jssystemapp.flight.application;

import org.jetsoft.web.jssystemapp.flight.domain.Flight;

public interface FlightRepository {
    void save(Flight flight);
    Flight get(Long id);
    void remove(Long id);
}
