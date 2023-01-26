package com.alexvanbeekum.theBucketOfHikes.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DuplicateException extends RuntimeException{
    String message;
    String localizedMessage;
}
