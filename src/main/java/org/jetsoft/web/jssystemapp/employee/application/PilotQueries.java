package org.jetsoft.web.jssystemapp.employee.application;

import java.util.List;

public interface PilotQueries {

    PilotDto getPilotDtoByPilotId(Long id);

    List<PilotDto> getAllAvailableToAssignPilotsDtoList();

    List<PilotDto> getAllPilotsAssignedToFlight(Long flightId);
}
