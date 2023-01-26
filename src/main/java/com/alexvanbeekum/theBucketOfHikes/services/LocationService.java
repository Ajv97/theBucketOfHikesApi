package com.alexvanbeekum.theBucketOfHikes.services;


import com.alexvanbeekum.theBucketOfHikes.exceptions.DuplicateException;
import com.alexvanbeekum.theBucketOfHikes.exceptions.EntityNotFoundException;
import com.alexvanbeekum.theBucketOfHikes.exceptions.ValidationException;
import com.alexvanbeekum.theBucketOfHikes.models.ListLocation;
import com.alexvanbeekum.theBucketOfHikes.models.Location;
import com.alexvanbeekum.theBucketOfHikes.repositories.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {
    final LocationRepository locationRepository;

    private static final Logger logger = LoggerFactory.getLogger(LocationService.class);

    @Transactional
    public boolean addLocation(Location location){
        if(locationRepository.existsByName(location.getName()))
            throw new DuplicateException("Location already exists",
                    "Name {" + location.getName() + "} already exists in database");

        if(location.getName() == null || location.getDescription() == null || location.getCountry() == null || location.getType() == null)
            throw new ValidationException("Missing location details");

        locationRepository.save(location);

        logger.info("Location " + location.getName() + " has been added");

        return true;
    }

    public Location getLocationById(Long locationId){
        return locationRepository.getReferenceById(locationId);
    }

    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    @Transactional
    public boolean removeLocation(Long locationId){
        if(!locationRepository.existsById(locationId)){
            throw new EntityNotFoundException("Location with Id \"" + locationId + "\" not found");
        }
        Location location = locationRepository.getReferenceById(locationId);

        return removeLocation(location);
    }

    @Transactional
    public boolean removeLocation( Location location){
        locationRepository.delete(location);
        logger.info("Location " + location.getName() + " has been removed");

        return true;
    }

    @Transactional
    public void updateLocation(Location location){
        Location update = locationRepository.findById(location.getId()).orElse(null);
        if(update == null){
            throw new EntityNotFoundException("Location with id {" + location.getId() + "} not found");
        }
        update.update(location);
    }

    public ArrayList<String> getCountries(){
        return locationRepository.selectAllCountries();
    }
    public ArrayList<String> getStatesByCountry(String country){
        return locationRepository.selectStatesByCountry(country);
    }
    public ArrayList<ListLocation> getLocationsByState(String state, String country){
        List<List<String>> queryList = locationRepository.selectAllLocationsByStateAndCountry(state, country);
        ArrayList<ListLocation> locations = new ArrayList<>();
        for (int i = 0; i < queryList.size(); i++) {
            locations.add(new ListLocation(queryList.get(i).get(0), queryList.get(i).get(1)));
        }
        return locations;
    }
    public ArrayList<ListLocation> getLocationsByCountry(String country) {
        List<List<String>> queryList = locationRepository.selectAllLocationsByCountryAndNullState(country);
        ArrayList<ListLocation> locations = new ArrayList<>();
        for (int i = 0; i < queryList.size(); i++) {
            locations.add(new ListLocation(queryList.get(i).get(0), queryList.get(i).get(1)));
        }
        return locations;
    }
}
