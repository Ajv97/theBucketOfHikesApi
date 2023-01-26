package com.alexvanbeekum.theBucketOfHikes.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Country {
    public String name;
    public List<State> states;
    public List<ListLocation> locations;

    public Country(String name){
        this.name = name;
    }

    public void addState(String stateName){
        if(states == null){
            states = new ArrayList<>();
        }
        states.add(new State(stateName));
    }

    public void addLocation(Long id, String location){
        addLocation(new ListLocation(id, location));
    }

    public void addLocation(ListLocation location){
        if(locations == null){
            locations = new ArrayList<>();
        }
        locations.add(location);
    }
}
