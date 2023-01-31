package org.jetsoft.web.jssystemapp.employee.application;

import org.jetsoft.web.jssystemapp.employee.api.PilotForm;
import org.jetsoft.web.jssystemapp.employee.domain.Pilot;
import org.jetsoft.web.jssystemapp.flight.application.FlightEmployeeRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.jetsoft.web.jssystemapp.flight.domain.PilotToFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PilotService {

    private final PilotRepository pilotRepository;
    private final FlightQueries flightQueries;
    private final EmployeeAccountQueries employeeAccountQueries;

    @Autowired
    PilotService(PilotRepository pilotRepository, FlightQueries flightQueries, EmployeeAccountQueries employeeAccountQueries) {
        this.pilotRepository = pilotRepository;
        this.flightQueries = flightQueries;
        this.employeeAccountQueries = employeeAccountQueries;
    }

    public void assignPilotToFlight(Long pilotId, Long flightId, String currentEmployeeLogin) {

        Long employeeId = employeeAccountQueries.getEmployeeAccountIdByUsername(currentEmployeeLogin);
        Pilot pilot = pilotRepository.get(pilotId);

        pilot.getFlights().add(new PilotToFlight(pilotId, flightId, employeeId, LocalDateTime.now()));

        pilotRepository.save(pilot);
        pilotRepository.flush();
    }

    public void removePilotFromFlight(Long pilotId, Long flightId) {

        pilotRepository.removePilotFromFlight(pilotId, flightId);
        pilotRepository.flush();
    }

    public void fillForm(@NonNull PilotForm form, @NonNull Long pilotId) {

        pilotRepository.find(pilotId).ifPresent(pilot -> fillFromEntity(form, pilot));
    }

    private void fillFromEntity(@NonNull PilotForm form, @NonNull Pilot pilot) {

        List<Long> assignedFLightsId = pilot.getFlights().stream()
                .map(PilotToFlight::getFlightId)
                .toList();

        List<FlightEmployeeRowDto> assignedFlights = flightQueries
                .getFlightEmployeeRowDtoListFilteredByIdList(assignedFLightsId);

        form.setPilotId(pilot.getId());
        form.setAssignedFlights(assignedFlights);
        form.setHoursOfExperience(pilot.getHoursFlown());
        form.setNationalityId(pilot.getNationalityId());
        form.setStatusId(pilot.getPilotStatusId());
        form.setLicenseNumber(pilot.getLicenseNumber());
    }
}
