package com.alexvanbeekum.theBucketOfHikes.models;

import static java.lang.Long.parseLong;

public class ListLocation {
    public Long id;
    public String name;

    public ListLocation(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public ListLocation(String id, String name) {
        this.id = parseLong(id);
        this.name = name;
    }

    @Override
    public String toString(){
        return "id: " + id + " \tName: " + name +"\n";
    }
}
