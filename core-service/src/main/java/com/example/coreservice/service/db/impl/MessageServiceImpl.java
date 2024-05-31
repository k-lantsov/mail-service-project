package com.example.coreservice.service.db.impl;

import com.example.coreservice.entity.FileType;
import com.example.coreservice.entity.Group;
import com.example.coreservice.entity.Message;
import com.example.coreservice.entity.Template;
import com.example.coreservice.exception.NoDataException;
import com.example.coreservice.model.broker.NewMessageEvent;
import com.example.coreservice.model.broker.StatusEvent;
import com.example.coreservice.repository.FileTypeRepository;
import com.example.coreservice.repository.GroupRepository;
import com.example.coreservice.repository.MessageRepository;
import com.example.coreservice.repository.TemplateRepository;
import com.example.coreservice.service.db.MessageService;
import com.example.coreservice.util.JsonConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private static final String MESSAGE_STATUS_INVALID = "INVALID";
    private static final String MESSAGE_STATUS_IN_PROGRESS= "IN PROGRESS";
    private static final String INVALID_TEMPLATE_ERROR_DESCRIPTION = "Invalid incoming request: template is not found";
    private static final String INVALID_USER_GROUP_ERROR_DESCRIPTION = "Invalid incoming request: user_group is not found";
    private static final String INVALID_FILE_TYPE_ERROR_DESCRIPTION = "Invalid incoming request: file_type is unavailable for sending";

    private final MessageRepository messageRepository;
    private final TemplateRepository templateRepository;
    private final GroupRepository groupRepository;
    private final FileTypeRepository fileTypeRepository;
    private final JsonConverter jsonConverter;

    @Transactional
    @Override
    public Message saveNewMessage(NewMessageEvent newMessageEvent) {
        Template template = templateRepository.findById(newMessageEvent.getTemplateId())
                .orElse(null);
        if (template == null) {
            Message message = Message.builder()
                    .uniqueMessage(newMessageEvent.getUniqueMessage())
                    .messageStatus(MESSAGE_STATUS_INVALID)
                    .created(LocalDateTime.now())
                    .updated(LocalDateTime.now())
                    .errorDescription(INVALID_TEMPLATE_ERROR_DESCRIPTION)
                    .incomingMessageRequest(jsonConverter.serializeToJson(newMessageEvent))
                    .build();
            return messageRepository.save(message);
        }

        Group userGroup = groupRepository.findById(newMessageEvent.getUserGroup())
                .orElse(null);
        if (userGroup == null) {
            Message message = Message.builder()
                    .uniqueMessage(newMessageEvent.getUniqueMessage())
                    .messageStatus(MESSAGE_STATUS_INVALID)
                    .template(template)
                    .created(LocalDateTime.now())
                    .updated(LocalDateTime.now())
                    .errorDescription(INVALID_USER_GROUP_ERROR_DESCRIPTION)
                    .incomingMessageRequest(jsonConverter.serializeToJson(newMessageEvent))
                    .build();
            return messageRepository.save(message);
        }

        return checkFileAndSaveMessage(newMessageEvent, template, userGroup);
    }

    @Transactional
    @Override
    public void updateMessageStatus(StatusEvent statusEvent) {
        Message message = messageRepository.findById(statusEvent.messageId())
                .orElseThrow(() -> new NoDataException("Message not found by id=" + statusEvent.messageId()));
        message.setMessageStatus(statusEvent.status());
        message.setErrorDescription(statusEvent.errorDescription());
        message.setUpdated(LocalDateTime.now());
    }

    private Message checkFileAndSaveMessage(NewMessageEvent newMessageEvent, Template template, Group userGroup) {
        if (newMessageEvent.getFile() != null && newMessageEvent.getFileType() != null) {
            FileType fileType = fileTypeRepository.findById(newMessageEvent.getFileType())
                    .orElse(null);
            Message message;
            if (fileType == null || !fileType.isAvailable()) {
                message = Message.builder()
                        .uniqueMessage(newMessageEvent.getUniqueMessage())
                        .messageStatus(MESSAGE_STATUS_INVALID)
                        .template(template)
                        .group(userGroup)
                        .created(LocalDateTime.now())
                        .updated(LocalDateTime.now())
                        .errorDescription(INVALID_FILE_TYPE_ERROR_DESCRIPTION)
                        .incomingMessageRequest(jsonConverter.serializeToJson(newMessageEvent))
                        .build();
            } else {
                byte[] decodedFile = Base64.getDecoder().decode(newMessageEvent.getFile());
                message = Message.builder()
                        .uniqueMessage(newMessageEvent.getUniqueMessage())
                        .messageStatus(MESSAGE_STATUS_IN_PROGRESS)
                        .template(template)
                        .group(userGroup)
                        .file(decodedFile)
                        .created(LocalDateTime.now())
                        .updated(LocalDateTime.now())
                        .incomingMessageRequest(jsonConverter.serializeToJson(newMessageEvent))
                        .build();
            }
            return messageRepository.save(message);
        }
        Message message = Message.builder()
                .uniqueMessage(newMessageEvent.getUniqueMessage())
                .messageStatus(MESSAGE_STATUS_IN_PROGRESS)
                .template(template)
                .group(userGroup)
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .incomingMessageRequest(jsonConverter.serializeToJson(newMessageEvent))
                .build();
        return messageRepository.save(message);
    }
}
