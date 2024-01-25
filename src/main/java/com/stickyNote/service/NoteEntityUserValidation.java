package com.stickyNote.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stickyNote.entity.NoteEntityUser;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class NoteEntityUserValidation {
    private final Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();
    public NoteEntityUser validateDetails(String requestBody){
        NoteEntityUser entityUser;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            entityUser = objectMapper.readValue(requestBody, NoteEntityUser.class);
        }
        catch (JsonProcessingException ex){
            throw new IllegalArgumentException("Verify that the input JSON data does not adhere to the expected JSON format.");
        }
        return entityUser;
    }
    public void validateViolations(NoteEntityUser noteEntity){
        Set<ConstraintViolation<NoteEntityUser>> violations = validator.validate(noteEntity);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<NoteEntityUser> violation : violations) {
                throw new IllegalArgumentException(violation.getMessage());
            }

        }


    }

}
