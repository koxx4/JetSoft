package org.jetsoft.web.jssystemapp.location.api;

import org.jetsoft.web.jssystemapp.location.application.RouteListRowDto;
import org.jetsoft.web.jssystemapp.location.application.RouteQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
class RouteListController {

    private final RouteQueries routeQueries;

    @Autowired
    RouteListController(RouteQueries routeQueries) {
        this.routeQueries = routeQueries;
    }

    @ModelAttribute("routes")
    private List<RouteListRowDto> addRoutesToModel() {

        return routeQueries.getRouteListRowDtoList();
    }

    @GetMapping("/employee/routeList")
    String getRouteListView() {

        return "route-list-view";
    }
}
