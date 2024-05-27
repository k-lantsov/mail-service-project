package com.example.coreservice.repository;

import com.example.coreservice.entity.FileType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileTypeRepository extends JpaRepository<FileType, String> {
}
