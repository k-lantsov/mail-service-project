package com.example.answeringservice.controller;

import com.example.answeringservice.config.rabbitmq.RabbitMQProducer;
import com.example.answeringservice.dto.AnswerMessageDto;
import com.example.answeringservice.dto.NewMessageDto;
import com.example.answeringservice.service.MessageService;
import com.example.answeringservice.util.MessageConverter;
import com.example.shared.model.NewMessageEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
    private static final String NEW_MESSAGES_QUEUE_NAME = "new-messages-queue";

    private final MessageConverter messageConverter;
    private final RabbitMQProducer rabbitMQProducer;
    private final MessageService messageService;

    @Operation(
            summary = "New message processing",
            description = "Starting the process of sending message to users"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful message processing")
    })
    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody @Valid NewMessageDto newMessageDto) {
        log.info("Got new message /// unique_message={}", newMessageDto.getUniqueMessage());
        NewMessageEvent newMessageEvent = messageConverter.convertNewMessageDtoToNewMessageEvent(newMessageDto);
        rabbitMQProducer.sendMessage(NEW_MESSAGES_QUEUE_NAME, newMessageEvent);
        log.info("Message was processed /// unique_message={}", newMessageEvent.getUniqueMessage());
        return ResponseEntity.ok("Message was processed");
    }

    @Operation(
            summary = "Get message info by uniqueMessage",
            description = "Get message info by uniqueMessage"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return actual information about message by uniqueMessage"
            )
    })
    @GetMapping("/answer/{uniqueMessage}")
    public ResponseEntity<AnswerMessageDto> getInfo(@PathVariable String uniqueMessage) {
        log.info("Got new message info request /// unique_message={}", uniqueMessage);
        AnswerMessageDto answerMessageDto = messageService.getInfo(uniqueMessage);
        return ResponseEntity.ok(answerMessageDto);
    }
}
