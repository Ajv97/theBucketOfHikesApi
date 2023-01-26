package com.alexvanbeekum.theBucketOfHikes.controllers;


import com.alexvanbeekum.theBucketOfHikes.exceptions.DuplicateException;
import com.alexvanbeekum.theBucketOfHikes.models.*;
import com.alexvanbeekum.theBucketOfHikes.payload.requests.AddLocationRequest;
import com.alexvanbeekum.theBucketOfHikes.payload.requests.LocationRequest;
import com.alexvanbeekum.theBucketOfHikes.payload.responses.ListResponse;
import com.alexvanbeekum.theBucketOfHikes.payload.responses.LocationResponse;
import com.alexvanbeekum.theBucketOfHikes.payload.responses.MessageResponse;
import com.alexvanbeekum.theBucketOfHikes.services.LinkService;
import com.alexvanbeekum.theBucketOfHikes.services.LocationService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("api")
@WebFilter("/filter-response-header/*")
public class LocationController implements Filter {
    final LocationService locationService;
    final LinkService linkService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        chain.doFilter(request, response);
    }

    /*
    POST request("api/location/")
    Body containing:
    {
        String name,
        String description,
        String country,
        String state (nullable),
        String lat,
        String lng,
        String type,
    }
     */
    @PostMapping(path = "/location", consumes = "application/json")
    public ResponseEntity<?> addLocation(@RequestBody AddLocationRequest addLocationRequest){
        Location location = new Location(addLocationRequest);

        try{
            locationService.addLocation(location);
        } catch (DuplicateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(HttpStatus.CONFLICT, e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new LocationResponse(location));
    }

    @GetMapping(path = "/location/{locationId}")
    public ResponseEntity<LocationResponse> getLocationById(@PathVariable Long locationId){
        Location location = locationService.getLocationById(locationId);

        return ResponseEntity.ok(new LocationResponse(location));
    }

    @GetMapping(path = "/locations")
    public ResponseEntity<ListResponse> getLocations(){
        List<Location> locations = locationService.getAllLocations();

        return ResponseEntity.ok(new ListResponse(locations));
    }

    @GetMapping(path = "/location/list")
    public ResponseEntity<ListResponse> getLocationList(){
        List<String> countryList = locationService.getCountries();
        List<Country> countries = new ArrayList<>();
        for (int i = 0; i < countryList.size(); i++) {
            countries.add(new Country(countryList.get(i)));
            List<String> stateList = locationService.getStatesByCountry(countryList.get(i));
            if(stateList.get(0) != null){
                for (int j = 0; j < stateList.size(); j++) {
                    countries.get(i).addState(stateList.get(j));
                    State state = countries.get(i).states.get(j);
                    List<ListLocation> locationList = locationService.getLocationsByState(stateList.get(j), countryList.get(i));
                    for (int k = 0; k < locationList.size(); k++) {
                        state.addLocation(locationList.get(k));
                    }
                }
            } else {
                List<ListLocation> locationList = locationService.getLocationsByCountry(countryList.get(i));
                for (int k = 0; k < locationList.size(); k++) {
                    countries.get(i).addLocation(locationList.get(k));
                }
            }
        }

        return ResponseEntity.ok(new ListResponse(countries));

    }




    @DeleteMapping(path="/location/{locationId}")
    public ResponseEntity<MessageResponse> removeLocation(@PathVariable Long locationId){
        locationService.removeLocation(locationId);

        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK, "Location Removed Successfully"));
    }

    @PutMapping("/location")
    public ResponseEntity<MessageResponse> updateLocation(@RequestBody LocationRequest locationRequest){
        Location location = new Location(locationRequest);
        //System.out.println(location);
        locationService.updateLocation(location);

        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK, "Location " + location.getName() + " has been successfully updated"));
    }

    @GetMapping("/searchList")
    public ResponseEntity<ListResponse> getSearchList(){
        List<SearchItem> searchItems = new ArrayList<>();
        List<Location> locations = locationService.getAllLocations();
        for (int i = 0; i < locations.size(); i++) {
            searchItems.add(new SearchItem(locations.get(i)));
        }
        List<Link> links = linkService.getAllLinks();
        for (int i=0; i<links.size(); i++){
            searchItems.add(new SearchItem(links.get(i)));
        }
        return ResponseEntity.ok(new ListResponse(searchItems));
    }
}
