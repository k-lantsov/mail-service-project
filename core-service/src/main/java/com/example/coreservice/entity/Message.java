package com.example.coreservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "message")
public class Message extends ParentEntity{

    @Column(name = "unique_message")
    private String uniqueMessage;

    @Column(name = "message_status")
    private String messageStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", referencedColumnName = "id")
    private Template template;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @Column(name = "file")
    private byte[] file;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "incoming_message_request")
    private String incomingMessageRequest;

    @Column(name = "error_description")
    private String errorDescription;
}
