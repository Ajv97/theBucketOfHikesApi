package com.alexvanbeekum.theBucketOfHikes.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationException extends RuntimeException{
    String message;
    String localizedMessage;

    public ValidationException(String message){
        this.message = message;
    }
}
