package com.alexvanbeekum.theBucketOfHikes.models;

import java.util.ArrayList;
import java.util.List;

public class State {
    public String name;
    public List<ListLocation> locations;

    public State(String name){
        this.name = name;
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
