package com.leozinn.redis.demo.exceptions;

public class UnprocessableEntity extends RuntimeException {

  public UnprocessableEntity(String message) {
    super(message);
  }
}