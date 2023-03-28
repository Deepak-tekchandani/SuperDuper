package com.example.registrationlogindemo.newModel.repository;

import com.example.registrationlogindemo.newModel.entity.CredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepo extends JpaRepository<CredentialsEntity,Long> {
}
