package com.alexvanbeekum.theBucketOfHikes.payload.responses;

import com.alexvanbeekum.theBucketOfHikes.models.Link;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class LinkResponse {
    HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
    LocalDateTime timestamp;
    Link link;

    public LinkResponse(Link link){
        this.link = link;
        status = HttpStatus.OK;
        timestamp = LocalDateTime.now();
    }
}
