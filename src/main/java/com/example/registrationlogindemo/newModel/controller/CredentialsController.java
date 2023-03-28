package com.example.registrationlogindemo.newModel.controller;

import com.example.registrationlogindemo.newModel.entity.CredentialsEntity;
import com.example.registrationlogindemo.newModel.entity.NotesEntity;
import com.example.registrationlogindemo.newModel.repository.CredentialsRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CredentialsController {

    @Autowired
    private CredentialsRepo credentialsRepo;

    private PasswordEncoder passwordEncoder;

    @RequestMapping("/credentials")
    public String list(Model model) {
        model.addAttribute("listCredentials", credentialsRepo.findAll());
//        return clientService.listsAll();
        return "credentials";
    }

    @GetMapping("/credentials/{id}")
    public CredentialsEntity get(@PathVariable Long id , Model model) {
        return credentialsRepo.findById(id).get();
    }
    @GetMapping("/credentials/getForUpdate/{id}")
    public String getForUpdate(@PathVariable Long id , Model model) {

        CredentialsEntity credentials =  credentialsRepo.findById(id).get();

        model.addAttribute("credentials", credentials);
        return "update_credentials";
    }

    @PostMapping("/credentials")
    public ResponseEntity<CredentialsEntity> createCredentials(@RequestBody CredentialsEntity credentials) {
        CredentialsEntity savedCredentials = null;
        savedCredentials.setUrl(credentials.getUrl());
        savedCredentials.setUsername(credentials.getUsername());
        savedCredentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        savedCredentials = credentialsRepo.save(savedCredentials);
        return new ResponseEntity<>(savedCredentials, HttpStatus.CREATED);
    }

    @PostMapping("/credentials/update")
    public String updateCredentials(@ModelAttribute("credentials") @Valid CredentialsEntity updatedCredentials) {
        CredentialsEntity existingCredentials = credentialsRepo.findById(updatedCredentials.getId()).get();
        existingCredentials.setUrl(updatedCredentials.getUrl());
        existingCredentials.setPassword(passwordEncoder.encode(updatedCredentials.getPassword()));
        existingCredentials.setUsername(updatedCredentials.getUsername());
        credentialsRepo.save(existingCredentials);
        return "redirect:/credentials";
    }

    @GetMapping("/credentials/delete/{id}")
    public String deleteNotes(@PathVariable Long id) {

        credentialsRepo.deleteById(id);
        return "redirect:/credentials";
    }


}
