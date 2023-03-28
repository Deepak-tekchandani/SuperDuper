package com.example.registrationlogindemo.newModel.repository;

import com.example.registrationlogindemo.newModel.entity.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepo extends JpaRepository<NotesEntity,Long> {
}
