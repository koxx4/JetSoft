package org.jetsoft.web.jssystemapp.vehicle.api;

import org.jetsoft.web.jssystemapp.vehicle.application.VehicleRowDto;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleQueries;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

@Controller
public class VehicleListController {

    private final VehicleQueries vehicleQueries;
    private final VehicleService vehicleService;
    private final VehicleFormValidator vehicleFormValidator;

    @Autowired
    public VehicleListController(VehicleQueries vehicleQueries,
                                 VehicleService vehicleService,
                                 VehicleFormValidator vehicleFormValidator) {
        this.vehicleQueries = vehicleQueries;
        this.vehicleService = vehicleService;
        this.vehicleFormValidator = vehicleFormValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(vehicleFormValidator);
    }

    @GetMapping("/vehicleList")
    String listVehicles(Model model) {

        List<VehicleRowDto> vehicles = vehicleQueries.getVehiclePublicRowDtoListPaginated(0, 50);

        model.addAttribute("vehicles", vehicles);

        return "vehicle-list-view";
    }
}
