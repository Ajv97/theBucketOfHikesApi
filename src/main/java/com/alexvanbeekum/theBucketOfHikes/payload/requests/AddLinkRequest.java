package com.alexvanbeekum.theBucketOfHikes.payload.requests;

import com.alexvanbeekum.theBucketOfHikes.enums.LinkType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddLinkRequest {
    private Long locationId;
    private String linkName;
    private String link;
    private LinkType type;
}
