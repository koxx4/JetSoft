package org.jetsoft.web.jssystemapp.employee.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.employee.application.*;
import org.jetsoft.web.jssystemapp.employee.domain.Pilot;
import org.jetsoft.web.jssystemapp.employee.domain.PilotStatus;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.jetsoft.web.jssystemapp.flight.domain.PilotToFlight;
import org.jetsoft.web.jssystemapp.location.application.CityAndNationalityQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
class JpaPilotQueries extends JpaQueries<Pilot> implements PilotQueries {

    private final EmployeeQueries employeeQueries;
    private final CityAndNationalityQueries cityAndNationalityQueries;
    private final FlightQueries flightQueries;

    @Autowired
    JpaPilotQueries(
            EntityManager entityManager,
            EmployeeQueries employeeQueries,
            CityAndNationalityQueries cityAndNationalityQueries,
            FlightQueries flightQueries) {

        super(entityManager, Pilot.class);
        this.employeeQueries = employeeQueries;
        this.cityAndNationalityQueries = cityAndNationalityQueries;
        this.flightQueries = flightQueries;
    }

    @Override
    public PilotDto getPilotDtoByPilotId(Long id) {

        Pilot pilot = getById(id);

        return toPilotDto(pilot);
    }

    @Override
    public List<PilotDto> getAllAvailableToAssignPilotsDtoList() {

        return getAll().stream()
                .filter(this::canPilotBeAssignedToFlight)
                .map(this::toPilotDto)
                .toList();
    }

    @Override
    @Transactional
    public List<PilotDto> getAllPilotsAssignedToFlight(Long flightId) {

        var criteriaBuilder = getCriteriaBuilder();
        var criteriaQuery = getCriteriaQuery(criteriaBuilder);
        Root<Pilot> root = criteriaQuery.from(Pilot.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("flights").get("flightId"), flightId));

        return getEntityManager().createQuery(criteriaQuery)
                .getResultList().stream()
                .map(this::toPilotDto)
                .toList();
    }

    @Override
    public List<String> getAllFlightNumbersAssignedToPilot(Long pilotId) {

        return findById(pilotId)
                .map(Pilot::getFlights)
                .orElse(Collections.emptyList()).stream()
                .map(PilotToFlight::getFlightId)
                .map(flightQueries::getFlightNumberByFlightId)
                .toList();
    }

    @Override
    public List<PilotListRowDto> getPilotListRowDtoList() {
        return getAll().stream()
                .map(this::toPilotListRowDto)
                .toList();
    }

    private PilotListRowDto toPilotListRowDto(Pilot pilot) {

        PilotStatus pilotStatus = getById(PilotStatus.class, pilot.getPilotStatusId());

        EmployeeFirstAndLastNameDto employeeFirstAndLastName = employeeQueries.getEmployeeFirstAndLastNameDto(pilot.getId());

        String nationalityName = cityAndNationalityQueries.getNationalityNameByNationalityId(pilot.getNationalityId());

        List<String> assignedFlightsNumbers = pilot.getFlights().stream()
                .map(PilotToFlight::getFlightId)
                .map(flightQueries::getFlightNumberByFlightId)
                .toList();

        return new PilotListRowDto(
                pilot.getId(),
                employeeFirstAndLastName.firstName(),
                employeeFirstAndLastName.lastName(),
                pilotStatus.getStatus(),
                nationalityName,
                pilot.getLicenseNumber(),
                pilot.getHoursFlown(),
                assignedFlightsNumbers
        );
    }

    private PilotDto toPilotDto(Pilot pilot) {

        PilotStatus pilotStatus = getById(PilotStatus.class, pilot.getPilotStatusId());
        EmployeeFirstAndLastNameDto employeeFirstAndLastName = employeeQueries.getEmployeeFirstAndLastNameDto(pilot.getId());
        String nationalityName = cityAndNationalityQueries.getNationalityNameByNationalityId(pilot.getNationalityId());

        return new PilotDto(
                pilot.getId(),
                employeeFirstAndLastName.firstName(),
                employeeFirstAndLastName.lastName(),
                pilotStatus.getStatus(),
                nationalityName,
                pilot.getHoursFlown()
        );
    }

    private boolean canPilotBeAssignedToFlight(Pilot pilot) {

        PilotStatus pilotStatus = getById(PilotStatus.class, pilot.getPilotStatusId());

        return pilotStatus.allowsPilotToBeAssignedToFlight();
    }
}
