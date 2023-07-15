package com.praveen.mqlearn.publisher;

import com.praveen.mqlearn.config.MqConfig;
import com.praveen.mqlearn.model.Photo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PhotosFeeder {

  @Autowired private RabbitTemplate template;

  @PostMapping("/photo")
  public ResponseEntity<Void> addPhoto(@RequestBody Photo photo) {

    template.convertAndSend(MqConfig.EXCHANGE, MqConfig.ROUTING_KEY, photo);
    return ResponseEntity.ok().build();
  }
}
