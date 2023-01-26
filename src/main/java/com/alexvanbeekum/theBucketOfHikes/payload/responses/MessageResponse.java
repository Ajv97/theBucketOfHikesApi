package com.alexvanbeekum.theBucketOfHikes.payload.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class MessageResponse {
    HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
    LocalDateTime timestamp;
    String message;

    public MessageResponse(String message){
        this(HttpStatus.OK, message);
    }

    public MessageResponse(HttpStatus status, String message){
        this.status = status;
        timestamp = LocalDateTime.now();
        this.message = message;
    }
}