package org.jetsoft.web.jssystemapp.vehicle.api;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.jetsoft.web.jssystemapp.vehicle.application.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleEditController {

    private final VehicleTypeQueries vehicleTypeQueries;
    private final VehicleModelQueries vehicleModelQueries;
    private final VehicleService vehicleService;
    private final VehicleFormValidator vehicleFormValidator;

    public VehicleEditController(VehicleTypeQueries vehicleTypeQueries,
                                 VehicleModelQueries vehicleModelQueries,
                                 VehicleService vehicleService,
                                 VehicleFormValidator vehicleFormValidator) {
        this.vehicleTypeQueries = vehicleTypeQueries;
        this.vehicleModelQueries = vehicleModelQueries;
        this.vehicleService = vehicleService;
        this.vehicleFormValidator = vehicleFormValidator;
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {

        binder.addValidators(vehicleFormValidator);
    }

    @ModelAttribute("vehicleTypeList")
    private List<VehicleTypeDto> addVehicleTypeDtoListToModel() {

        return vehicleTypeQueries.getVehicleTypeDtoList();
    }

    @ModelAttribute("vehicleModelList")
    private List<VehicleModelDto> addVehicleModelDtoListToModel() {

        return vehicleModelQueries.getVehicleModelDtoList();
    }

    @GetMapping("/editVehicle")
    String saveVehicle(@RequestParam(required = false) Long id, Model model) {

        if (id == null) {

            model.addAttribute("vehicleForm", new VehicleForm());

            return "vehicle-edit-view";
        }

        var filledForm = vehicleService.getFilledFormFromVehicle(id);

        model.addAttribute("vehicleForm", filledForm);

        return "vehicle-edit-view";
    }

    @PostMapping("/addVehicle")
    String saveFlight(@ModelAttribute @Valid VehicleForm vehicleForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "flight-edit-view";
        }

        vehicleService.saveVehicleFromForm(vehicleForm);

        return "redirect:/vehicleList";
    }
}
