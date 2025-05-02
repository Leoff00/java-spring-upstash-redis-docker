package com.leozinn.redis.demo.error;

public class ErrorResponse {
  private final String message;
  private final String timestamp;

  public ErrorResponse(String message) {
    this.message = message;
    this.timestamp = java.time.ZonedDateTime.now().toString();
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

}