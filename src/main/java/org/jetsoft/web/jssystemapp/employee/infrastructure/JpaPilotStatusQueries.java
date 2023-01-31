package org.jetsoft.web.jssystemapp.employee.infrastructure;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.employee.application.PilotStatusDto;
import org.jetsoft.web.jssystemapp.employee.application.PilotStatusQueries;
import org.jetsoft.web.jssystemapp.employee.domain.PilotStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class JpaPilotStatusQueries extends JpaQueries<PilotStatus> implements PilotStatusQueries {

    @Autowired
    protected JpaPilotStatusQueries(EntityManager entityManager) {

        super(entityManager, PilotStatus.class);
    }

    @Override
    public List<PilotStatusDto> getStatusList() {

        return getAll().stream()
                .map(this::toPilotStatusDto)
                .toList();
    }

    private PilotStatusDto toPilotStatusDto(PilotStatus status) {

        return new PilotStatusDto(status.getId(), status.getStatus());
    }
}
