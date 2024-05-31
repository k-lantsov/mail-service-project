package com.example.answeringservice.repository;

import com.example.answeringservice.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
