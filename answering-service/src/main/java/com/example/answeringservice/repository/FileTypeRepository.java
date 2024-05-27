package com.example.answeringservice.repository;

import com.example.answeringservice.entity.FileType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileTypeRepository extends JpaRepository<FileType, String> {
}
