package com.stickyNote.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stickyNote.entity.NoteEntity;
import com.stickyNote.service.NoteEntityService;
import com.stickyNote.service.NoteEntityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class StickyNoteHandler {
    @Autowired
    NoteEntityValidation noteEntityValidation;
    @Autowired
    NoteEntityService service;
    @PostMapping("/create")
    public ResponseEntity<String> createStickyNote(@RequestBody String requestBody) {


        NoteEntity noteEntity = noteEntityValidation.validateNoteEntity(requestBody);
        service.saveNoteDetails(noteEntity);

        return new ResponseEntity<>("Response Saved Successfully", HttpStatus.OK);
    }
    @GetMapping("/viewAll")
    public ResponseEntity<String> viewAllStickyNotes(){
        List<NoteEntity> list = service.getAllStickyDetails();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = null;
        try{
            jsonData = objectMapper.writeValueAsString(list);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStickyNote(@RequestBody String requestBody){
        NoteEntity noteEntity = noteEntityValidation.validateNoteEntity(requestBody);
        service.deleteById(noteEntity.getId());
        return new ResponseEntity<>(noteEntity.getId()+" Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStickyNote(@RequestBody String requestBody){
        NoteEntity noteEntity = noteEntityValidation.validateNoteEntity(requestBody);
        service.updateNote(noteEntity);
        return new ResponseEntity<>(noteEntity.getId()+" Updated Successfully", HttpStatus.OK);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An error occurred: ",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
