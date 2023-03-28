package com.example.registrationlogindemo.newModel.controller;

import com.example.registrationlogindemo.newModel.entity.NotesEntity;
import com.example.registrationlogindemo.newModel.repository.NotesRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotesController {

    @Autowired
    private NotesRepo notesRepo;

    @RequestMapping("/notes")
    public String list(Model model) {
        model.addAttribute("listNotes", notesRepo.findAll());
//        return clientService.listsAll();
        return "notes";
    }

    @GetMapping("/notes/{id}")
    public NotesEntity get(@PathVariable Long id , Model model) {
        return notesRepo.findById(id).get();
    }
    @GetMapping("/notes/getForUpdate/{id}")
    public String getForUpdate(@PathVariable Long id , Model model) {

        NotesEntity notes =  notesRepo.findById(id).get();

        model.addAttribute("notes", notes);
        return "update_notes";
    }

    @PostMapping("/notes")
    public ResponseEntity<NotesEntity> createNotes(@RequestBody NotesEntity notes) {
        NotesEntity savedNotes = notesRepo.save(notes);
        return new ResponseEntity<>(savedNotes, HttpStatus.CREATED);
    }

    @PostMapping("/notes/update")
    public String updateNotes(@ModelAttribute("notes") @Valid NotesEntity updatedNotes) {
        NotesEntity existingNotes = notesRepo.findById(updatedNotes.getId()).get();
        existingNotes.setTitle(updatedNotes.getTitle());
        existingNotes.setDescription(updatedNotes.getDescription());
        notesRepo.save(existingNotes);
        return "redirect:/notes";
    }

    @GetMapping("/notes/delete/{id}")
    public String deleteNotes(@PathVariable Long id) {

        notesRepo.deleteById(id);
        return "redirect:/notes";
    }
}
