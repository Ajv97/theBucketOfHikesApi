package com.alexvanbeekum.theBucketOfHikes.payload.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ListResponse {
    HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
    LocalDateTime timestamp;
    List<?> objects;

    public ListResponse(List<?> objects) {
        this.objects = objects;
        status = HttpStatus.OK;
        timestamp = LocalDateTime.now();
    }
}
