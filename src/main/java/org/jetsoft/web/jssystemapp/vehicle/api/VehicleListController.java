package org.jetsoft.web.jssystemapp.vehicle.api;

import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.vehicle.application.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

        List<VehicleRowDto> vehicles = vehicleQueries.getVehicleRowDtoListPaginated(0, 50);

        model.addAttribute("vehicles", vehicles);

        return "vehicle-list-view";
    }

    @DeleteMapping("/deleteVehicle")
    String listVehicles(@RequestParam Long deleteId) {

        vehicleService.removeVehicleById(deleteId);

        return "redirect:/vehicleList";
    }
}
