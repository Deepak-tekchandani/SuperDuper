package com.example.registrationlogindemo.newModel.controller;

import com.example.registrationlogindemo.newModel.entity.CredentialsEntity;
import com.example.registrationlogindemo.newModel.repository.CredentialsRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
public class CredentialsController {

    @Autowired
    private CredentialsRepo credentialsRepo;

    private PasswordEncoder passwordEncoder;

    @GetMapping("/credential")
    public String list(Model model) {
        model.addAttribute("listCredentials", credentialsRepo.findAll());
//        return clientService.listsAll();
        return "credential";
    }

    @GetMapping("/credential/new")
    public String showNewOrderForm(Model model)
    {
        //create model attribute to bind Form data
        CredentialsEntity credential = new CredentialsEntity();
        model.addAttribute("credential", credential);
        return "add_credential";
    }

    // Crée une nouvelle commande
    @PostMapping("/credential/save")
    public String create(@ModelAttribute("credential") @Valid CredentialsEntity credential , Errors errors) {
        if (errors.hasErrors())
        {
            return "credential";
        }
        CredentialsEntity credentials = null;
        credentials.setUrl(credential.getUrl());
        credentials.setUsername(credential.getUsername());
        credentials.setPassword(passwordEncoder.encode(credential.getPassword()));
        credentialsRepo.save(credentials);
        return "redirect:/credential";
    }

    // Récupère une commande par ID
    @GetMapping("/credential/{id}")
    public CredentialsEntity get(@PathVariable Long id ,Model model) {

        return credentialsRepo.findById(id).get();
    }

    @GetMapping("/credential/getForUpdate/{id}")
    public String getForUpdate(@PathVariable Long id ,Model model) {

        CredentialsEntity credential =  credentialsRepo.findById(id).get();
        model.addAttribute("credential", credential);
        return "credential";
    }

    // Met à jour une commande existante par ID
    @PostMapping("/credential/update")
    public String update(@ModelAttribute("credential") @Valid CredentialsEntity updatedCredential) {
        // Récupère la commande existante
        CredentialsEntity existingCredential = credentialsRepo.findById(updatedCredential.getId()).get();
        if (existingCredential != null) {
            // Met à jour les informations de la commande existante
            existingCredential.setUrl(updatedCredential.getUrl());
            existingCredential.setUsername(updatedCredential.getUsername());
            existingCredential.setPassword(passwordEncoder.encode(updatedCredential.getPassword()));
            // Enregistre la commande mise à jour
            credentialsRepo.save(existingCredential);
        }
        return "redirect:/credential";
    }

    // Supprime une commande par ID
    @GetMapping("/credential/delete/{id}")
    public String delete(@PathVariable Long id) {
        credentialsRepo.deleteById(id);
        return "redirect:/credential";
    }


}
