package com.leozinn.redis.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leozinn.redis.demo.exceptions.KeyNotFound;
import com.leozinn.redis.demo.exceptions.UnprocessableEntity;

@Validated
@RestController
public class ExController {
  private static final Logger logger = LoggerFactory.getLogger(ExController.class);

  private final RedisTemplate<String, Object> template;

  public ExController(RedisTemplate<String, Object> template) {
    this.template = template;
  }

  @GetMapping("/get")
  public ResponseEntity<Map<String, String>> getCache(@RequestParam(required = false) String key) {
    Map<String, String> JSONResponse = new HashMap<>();

    String value = Optional.ofNullable(String.valueOf(template.opsForValue().get(key)))
        .map(Object::toString)
        .orElseThrow(() -> new KeyNotFound("Key not found"));

    JSONResponse.put("data", value);
    logger.info("Getting value in successfully {}", value);

    return ResponseEntity.ok().body(JSONResponse);
  }

  @PostMapping("/set")
  public ResponseEntity<?> setCache(@RequestBody Map<String, String> dto) {
    for (Map.Entry<String, String> entry : dto.entrySet()) {
      if (template.hasKey(entry.getKey())) {
        throw new UnprocessableEntity("Key is already in cache");
      }
      template.opsForValue().set(entry.getKey(), entry.getValue());
      logger.info("DTO saving in cache successfully");
    }

    return ResponseEntity.noContent().build();
  }
}
