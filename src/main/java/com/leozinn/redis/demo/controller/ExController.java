package com.leozinn.redis.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leozinn.redis.demo.DTOs.DTO;

@RestController
public class ExController {
  private static final Logger logger = LoggerFactory.getLogger(ExController.class);

  private final RedisTemplate<String, Object> template;

  public ExController(RedisTemplate<String, Object> template) {
    this.template = template;
  }

  @GetMapping("/get")
  @ResponseBody
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<Object> getCache(@RequestParam(required = false) String key) {
    Map<String, Object> JSONResponse = new HashMap<>();
    String value = Optional.ofNullable(template.opsForValue().get(key))
        .map(Object::toString).orElse("Not Found");
    
        JSONResponse.put("data", value);
        logger.info("Getting value in successfully {}", value);

    return ResponseEntity.ok(JSONResponse);
  }

  @PostMapping("/set")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public ResponseEntity<Object> setCache(@RequestBody DTO dto) {
    logger.info("DTO saving in cache successfully");

    template.opsForValue().set("foo", dto.key());
    return ResponseEntity.ok(null);
  }

}
