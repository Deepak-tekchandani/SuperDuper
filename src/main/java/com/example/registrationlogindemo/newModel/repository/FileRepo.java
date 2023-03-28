package com.example.registrationlogindemo.newModel.repository;

import com.example.registrationlogindemo.newModel.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<FileEntity,Long> {
}
