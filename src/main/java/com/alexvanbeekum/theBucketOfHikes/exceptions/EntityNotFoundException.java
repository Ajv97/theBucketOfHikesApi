package com.alexvanbeekum.theBucketOfHikes.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityNotFoundException extends RuntimeException{
    String message;
}
