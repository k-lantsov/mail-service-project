package com.example.coreservice.service.processor.impl;

import com.example.coreservice.entity.Message;
import com.example.coreservice.entity.Template;
import com.example.coreservice.rabbitmq.RabbitMQProducer;
import com.example.coreservice.repository.UserRepository;
import com.example.coreservice.service.db.MessageService;
import com.example.coreservice.service.processor.NewMessageEventProcessor;
import com.example.shared.model.MailEvent;
import com.example.shared.model.NewMessageEvent;
import com.example.shared.model.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitNewMessageEventProcessor implements NewMessageEventProcessor {

    private static final String MESSAGE_STATUS_INVALID = "INVALID";

    private static final String DAY_TAG = "$day";
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final RabbitMQProducer rabbitMQProducer;

    @Override
    public void processEvent(NewMessageEvent newMessageEvent) {
        log.info("New message was received /// unique_message={}", newMessageEvent.getUniqueMessage());

        Message newMessage = messageService.saveNewMessage(newMessageEvent);
        log.info("New message was saved /// unique_message={}", newMessageEvent.getUniqueMessage());

        if (MESSAGE_STATUS_INVALID.equals(newMessage.getMessageStatus())) {
            return;
        }

        MailEvent mailEvent = createMailEvent(newMessage, newMessageEvent);
        rabbitMQProducer.sendMessage("mail-queue", mailEvent);
        log.info("MailEvent was sent /// message_id={}", mailEvent.messageId());
    }

    private MailEvent createMailEvent(Message newMessage, NewMessageEvent newMessageEvent) {
        Template template = newMessage.getTemplate();
        byte[] file = newMessage.getFile();
        long groupId = newMessage.getGroup().getId();
        List<String> emails = userRepository.getEmailsByGroup(groupId);
        String messageText = constructMessageText(newMessageEvent, template);
        String fileType = file == null ? null : newMessageEvent.getFileType();
        return new MailEvent(newMessage.getId(), emails, messageText, file, fileType);
    }

    private String constructMessageText(NewMessageEvent newMessageEvent, Template template) {
        Tags data = newMessageEvent.getTags();
        if (data == null) {
            return template.getTemplateText();
        }
        String messageText = template.getTemplateText();
        if (data.getDay() != null) {
            messageText = messageText.replace(DAY_TAG, data.getDay());
        }
        return messageText;
    }
}
