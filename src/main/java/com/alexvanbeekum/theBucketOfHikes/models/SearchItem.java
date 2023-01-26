package com.alexvanbeekum.theBucketOfHikes.models;

public class SearchItem {
    public Long locationId;
    public String title;
    public String type;

    public SearchItem(Long locationId, String title, String type){
        this.locationId = locationId;
        this.title = title;
        this.type = type;
    }

    public SearchItem(Location location){
        this(location.getId(), location.getName(), "Location");
    }
    public SearchItem(Link link){
        this(link.getLocationId(), link.getLinkName(), "Link");
    }

}
