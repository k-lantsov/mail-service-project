package com.example.answeringservice.repository;

import com.example.answeringservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByUniqueMessage(String uniqueMessage);
}
