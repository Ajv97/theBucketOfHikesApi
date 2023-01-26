package com.alexvanbeekum.theBucketOfHikes.payload.requests;

import com.alexvanbeekum.theBucketOfHikes.enums.LightClass;
import com.alexvanbeekum.theBucketOfHikes.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddLocationRequest {
    private String name;
    private String description;
    private String country;
    private String state;
    private String lat;
    private String lng;
    private LocationType type;
    private LightClass lightClass;
    private Long elevation;
}
