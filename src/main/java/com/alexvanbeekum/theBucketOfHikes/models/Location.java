package com.alexvanbeekum.theBucketOfHikes.models;

import com.alexvanbeekum.theBucketOfHikes.enums.LightClass;
import com.alexvanbeekum.theBucketOfHikes.enums.LocationType;
import com.alexvanbeekum.theBucketOfHikes.payload.requests.AddLocationRequest;
import com.alexvanbeekum.theBucketOfHikes.payload.requests.LocationRequest;
import com.fasterxml.jackson.annotation.JsonTypeId;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.lang.Float.parseFloat;

@Entity
@NoArgsConstructor
@Getter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String country;
    private String state;
    private Float lat;
    private Float lng;
    private LocationType type;
    private LightClass lightClass;
    private Long elevation;

    public Location(String name, String description, String country, String state, Float lat, Float lng, LocationType type, LightClass lightClass, Long elevation) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.state = state;
        this.lat = lat;
        this.lng = lng;
        this.type = type;
        this.lightClass = lightClass;
        this.elevation = elevation;
    }

    public Location(LocationRequest locationRequest){
        this.id = locationRequest.getId();
        this.name = locationRequest.getName();
        this.description = locationRequest.getDescription();
        this.country = locationRequest.getCountry();
        this.state = locationRequest.getState();
        this.lat = parseFloat(locationRequest.getLat());
        this.lng = parseFloat(locationRequest.getLng());
        this.type = locationRequest.getType();
        this.lightClass = locationRequest.getLightClass();
        this.elevation = locationRequest.getElevation();
    }

    public Location(AddLocationRequest locationRequest){
        this.name = locationRequest.getName();
        this.description = locationRequest.getDescription();
        this.country = locationRequest.getCountry();
        this.state = locationRequest.getState();
        this.lat = parseFloat(locationRequest.getLat());
        this.lng = parseFloat(locationRequest.getLng());
        this.type = locationRequest.getType();
        this.lightClass = locationRequest.getLightClass();
        this.elevation = locationRequest.getElevation();
    }

    public void update(Location location){
        this.name = location.name;
        this.description = location.description;
        this.country = location.country;
        this.state = location.state;
        this.lat = location.lat;
        this.lng = location.lng;
        this.type = location.type;
        this.lightClass = location.lightClass;
        this.elevation = location.elevation;
    }

    @Override
    public String toString(){
        String str = "id      :" + this.id;
        str +="\nname    :" + this.name;
        str +="\ncountry :" + this.country;
        str +="\nstate   :" + this.state;
        str +="\nlat     :" + this.lat;
        str +="\nlng     :" + this.lng;
        str +="\ntype    :" + this.type;
        str +="\ndescription:" + this.description;
        str +="\nlight class:" + this.lightClass;
        str +="\nelevation  :" + this.elevation;
        return str;
    }
}
