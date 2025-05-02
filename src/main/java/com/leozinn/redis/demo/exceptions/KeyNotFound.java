package com.leozinn.redis.demo.exceptions;

public class KeyNotFound extends RuntimeException {
  public KeyNotFound(String message) {
    super(message);
  }

}
