package org.jetsoft.web.jssystemapp.flight.application;

import org.jetsoft.web.jssystemapp.flight.api.FlightForm;
import org.jetsoft.web.jssystemapp.flight.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightQueries flightQueries;

    @Autowired
    public FlightService(FlightRepository flightRepository, FlightQueries flightQueries) {

        this.flightRepository = flightRepository;
        this.flightQueries = flightQueries;
    }

    public void removeFlightById(Long id) {

        flightRepository.remove(id);
    }

    public void saveFlightFromForm(FlightForm flightForm) {

        if (flightQueries.exists(flightForm.getId())) {

            Flight flight = flightRepository.get(flightForm.getId());

            updateFlight(flight, flightForm);

            flightRepository.save(flight);

            return;
        }

        flightRepository.save(toFlight(flightForm));
    }

    public FlightForm getFilledFormFromFlight(Long id) {

        var form = new FlightForm();

        var flight = flightRepository.get(id);

        form.setVehicleId(flight.getVehicleId());
        form.setRouteId(flight.getRouteId());
        form.setFlightNumber(flight.getFlightNumber());
        form.setPlannedDeparture(flight.getPlannedDeparture());
        form.setPlannedArrival(flight.getPlannedArrival());
        form.setId(flight.getId());
        form.setActive(flight.isActive());
        form.setArchival(flight.isArchival());
        form.setConfidential(flight.isConfidential());
        form.setMinPilotCount(flight.getMinPilotCount());
        form.setAvailablePassengersSeats(flight.getAvailablePassengersSeats());

        return form;
    }

    private void updateFlight(Flight flight, FlightForm flightForm) {

        flight.setFlightNumber(flightForm.getFlightNumber());
        flight.setActive(flightForm.isActive());
        flight.setArchival(flightForm.isArchival());
        flight.setConfidential(flightForm.isConfidential());
        flight.setAvailablePassengersSeats(flightForm.getAvailablePassengersSeats());
        flight.setRouteId(flightForm.getRouteId());
        flight.setPlannedArrival(flightForm.getPlannedArrival());
        flight.setPlannedDeparture(flightForm.getPlannedDeparture());
        flight.setVehicleId(flightForm.getVehicleId());
        flight.setMinPilotCount(flightForm.getMinPilotCount());
        flight.setLastModificationDate(LocalDateTime.now());
    }

    private Flight toFlight(FlightForm flightForm) {

        LocalDateTime now = LocalDateTime.now();

        return new Flight(
                flightForm.getRouteId(),
                flightForm.getVehicleId(),
                null,
                flightForm.isActive(),
                flightForm.getFlightNumber(),
                flightForm.getAvailablePassengersSeats(),
                flightForm.getMinPilotCount(),
                flightForm.isConfidential(),
                flightForm.isArchival(),
                now,
                flightForm.getPlannedDeparture(),
                flightForm.getPlannedArrival(),
                Collections.emptyList()
        );
    }
}
