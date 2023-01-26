package com.alexvanbeekum.theBucketOfHikes.models;

import com.alexvanbeekum.theBucketOfHikes.enums.LinkType;
import com.alexvanbeekum.theBucketOfHikes.payload.requests.AddLinkRequest;
import com.alexvanbeekum.theBucketOfHikes.payload.requests.LinkRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long linkId;
    private Long locationId;
    private String linkName;
    private String link;
    private LinkType type;

    public Link(AddLinkRequest linkRequest){
        this.locationId = linkRequest.getLocationId();
        this.linkName = linkRequest.getLinkName();
        this.link = linkRequest.getLink();
        this.type = linkRequest.getType();
    }
    public Link(LinkRequest linkRequest){
        this.linkId = linkRequest.getLinkId();
        this.locationId = linkRequest.getLocationId();
        this.linkName = linkRequest.getLinkName();
        this.link = linkRequest.getLink();
        this.type = linkRequest.getType();
    }
    public void update(Link link){
        this.linkName = link.linkName;
        this.link = link.link;
        this.type = link.type;
    }

    @Override
    public String toString(){
        return "\nlinkId     :" + this.linkId +
               "\nlocationId :" + this.locationId +
               "\nlink name  :" + this.linkName +
               "\nlink       :" + this.link +
               "\ntype       :" + this.type;
    }
}