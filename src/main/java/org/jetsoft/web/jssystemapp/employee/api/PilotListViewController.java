package org.jetsoft.web.jssystemapp.employee.api;

import org.jetsoft.web.jssystemapp.employee.application.PilotListRowDto;
import org.jetsoft.web.jssystemapp.employee.application.PilotQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public
class PilotListViewController {

    private static final String V_PILOT_LIST = "pilot-list-view";

    private final PilotQueries pilotQueries;

    @Autowired
    PilotListViewController(PilotQueries pilotQueries) {
        this.pilotQueries = pilotQueries;
    }


    @ModelAttribute("pilots")
    private List<PilotListRowDto> addPilotListRowDtoListToModel() {

        return pilotQueries.getPilotListRowDtoList();
    }

    @GetMapping("/employee/pilotList")
    String getPilotList() {

        return V_PILOT_LIST;
    }
}
