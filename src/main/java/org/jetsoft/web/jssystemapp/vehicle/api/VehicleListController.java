package org.jetsoft.web.jssystemapp.vehicle.api;

import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.flight.api.FlightForm;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleRowDto;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleQueries;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/editVehicle")
    String saveFlight(@RequestParam(required = false) Long id, Model model) {

        addVehicleModelDtoList(model);

        if (id == null) {

            model.addAttribute("vehicleForm", new VehicleForm());

            return "vehicle-edit-view";
        }

        var filledForm = vehicleService.getFilledFormFromVehicle(id);

        model.addAttribute("vehicleForm", filledForm);

        return "vehicle-edit-view";
    }

    @PostMapping("/addVehicle")
    String saveVehicle(@ModelAttribute @Valid VehicleForm vehicleForm, BindingResult bindingResult)  {

        if (bindingResult.hasErrors()) {

            return "vehicle-edit-view";
        }

        vehicleService.saveVehicleFromForm(vehicleForm);

        return "redirect:/vehicleForm";
    }

    private void addVehicleModelDtoList(Model model) {

        var vehicleModelList = vehicleQueries.getVehicleModelInVehicleFormDtoList();

        model.addAttribute("vehicleModelList", vehicleModelList);
    }

    @DeleteMapping("/deleteVehicle")
    String deleteVehicle(@RequestParam Long deleteId, Model model) {

        Assert.notNull(deleteId, "id cannot be null!");

        vehicleService.removeVehicleById(deleteId);

        model.addAttribute("deletedV", true);

        return "redirect:/vehicleList";
    }
}
