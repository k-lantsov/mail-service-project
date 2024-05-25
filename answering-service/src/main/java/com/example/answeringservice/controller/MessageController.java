package com.example.answeringservice.controller;

import com.example.answeringservice.rabbitmq.RabbitMQProducer;
import com.example.answeringservice.dto.NewMessageDto;
import com.example.answeringservice.util.JsonConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final JsonConverter jsonConverter;
    private final RabbitMQProducer rabbitMQProducer;

    @Operation(
            summary = "New message processing",
            description = "Starting the process of sending message to users"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful message processing")
    })
    @PostMapping(value = "/send")
    public ResponseEntity<String> send(@RequestBody @Valid NewMessageDto newMessageDto) {
        log.info("Got new message /// unique_message={}", newMessageDto.getUniqueMessage());
        String message = jsonConverter.serializeToJson(newMessageDto);
        rabbitMQProducer.sendMessage(message);
        log.info("Message was processed /// unique_message={}", newMessageDto.getUniqueMessage());
        return ResponseEntity.ok("Message was processed");
    }
}
