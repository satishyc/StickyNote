package com.stickyNote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stickyNote.entity.NoteEntityUser;
import com.stickyNote.service.NoteEntityUserService;
import com.stickyNote.service.NoteEntityUserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2")
public class StickyNoteHandlerWithUsers {
    @Autowired
    NoteEntityUserValidation validation;

    @Autowired
    NoteEntityUserService service;
    @PostMapping("/create")
    public ResponseEntity<String> createStickyNote(@RequestParam String note,String userName){
        NoteEntityUser entity = new NoteEntityUser();
        entity.setNote(note);
        entity.setUserName(userName);
        validation.validateViolations(entity);
        service.saveEntityDetails(entity);
        return new ResponseEntity<>("Response Saved Successfully", HttpStatus.OK);
    }
    @GetMapping("/viewAll")
    public ResponseEntity<String> viewAllNotes(){
        List<NoteEntityUser> list = service.findAllDetails();
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

    @GetMapping("/viewById")
    public  ResponseEntity<String> viewStickyNoteById(@RequestParam String id){
        NoteEntityUser entity = service.getDetailsById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = null;
        try{
            jsonData = objectMapper.writeValueAsString(entity);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }
    @GetMapping("/viewByUserName")
    public ResponseEntity<String> viewStickyNotesByUserName(@RequestParam String userName){
        List<NoteEntityUser> entity = service.findByUserNameDetails(userName);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = null;
        try{
            jsonData = objectMapper.writeValueAsString(entity);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStickyNoteById(@RequestParam String id,String userName){
        service.deleteById(id,userName);
        return new ResponseEntity<>(id+" Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateData(@RequestParam  NoteEntityUser entity){
        validation.validateViolations(entity);
        service.updateDetails(entity);
        return new ResponseEntity<>(entity.getId()+" Updated Successfully", HttpStatus.OK);

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
