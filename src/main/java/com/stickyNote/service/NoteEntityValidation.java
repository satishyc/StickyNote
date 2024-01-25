package com.stickyNote.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stickyNote.entity.NoteEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class NoteEntityValidation {
    private final Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();

    public NoteEntity validateNoteEntity(String requestBody){
        NoteEntity noteEntity;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            noteEntity = objectMapper.readValue(requestBody, NoteEntity.class);
        }
        catch (JsonProcessingException ex){
            throw new IllegalArgumentException("Verify that the input JSON data does not adhere to the expected JSON format.");
        }
        return noteEntity;
    }
    public void validateViolations(NoteEntity noteEntity){
        Set<ConstraintViolation<NoteEntity>> violations = validator.validate(noteEntity);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<NoteEntity> violation : violations) {
                throw new IllegalArgumentException(violation.getMessage());
            }

        }


    }

}
