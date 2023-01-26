package com.alexvanbeekum.theBucketOfHikes.payload.responses;

import com.alexvanbeekum.theBucketOfHikes.models.Location;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class LocationResponse {
    HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
    LocalDateTime timestamp;
    Location location;

    public LocationResponse(Location location){
        this.location = location;
        status = HttpStatus.OK;
        timestamp = LocalDateTime.now();
    }
}
