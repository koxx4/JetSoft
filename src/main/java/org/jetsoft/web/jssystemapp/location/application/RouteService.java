package org.jetsoft.web.jssystemapp.location.application;

import org.jetsoft.web.jssystemapp.location.api.RouteForm;
import org.jetsoft.web.jssystemapp.location.domain.City;
import org.jetsoft.web.jssystemapp.location.domain.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteQueries routeQueries;
    private final CityRepository cityRepository;

    @Autowired
    RouteService(RouteRepository routeRepository, RouteQueries routeQueries, CityRepository cityRepository) {
        this.routeRepository = routeRepository;
        this.routeQueries = routeQueries;
        this.cityRepository = cityRepository;
    }

    public void fillRouteForm(@NonNull RouteForm form, @NonNull Long routeId) {

        Optional<Route> optionalRoute = routeRepository.find(routeId);

        optionalRoute.ifPresent(route -> fillRouteFormFromEntity(form, route));
    }

    public void updateOrCreateRouteFromForm(RouteForm routeForm) {

        if (routeQueries.exists(routeForm.getRouteId())) {

            Route route = routeRepository.get(routeForm.getRouteId());
            City source = cityRepository.get(routeForm.getSourceCityId());
            City destination = cityRepository.get(routeForm.getDestinationCityId());

            route.setDistance(routeForm.getDistance());
            route.setSourceCity(source);
            route.setDestinationCity(destination);

            routeRepository.save(route);

            return;
        }

        City source = cityRepository.get(routeForm.getSourceCityId());
        City destination = cityRepository.get(routeForm.getDestinationCityId());

        Route newRoute = new Route(source, destination, routeForm.getDistance());

        routeRepository.save(newRoute);
    }

    public void deleteRouteById(Long routeId) {

        routeRepository.remove(routeId);
    }

    private void fillRouteFormFromEntity(@NonNull RouteForm form, @NonNull Route route) {

        form.setRouteId(route.getId());
        form.setSourceCityId(route.getSourceCity().getId());
        form.setDestinationCityId(route.getDestinationCity().getId());
        form.setDistance(route.getDistance());
    }
}
