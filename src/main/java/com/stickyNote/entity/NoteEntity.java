package com.stickyNote.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sticky_note")
public class NoteEntity {
    @Id
    private String id;

    @NotEmpty(message = "Note cannot be blank")
    @Size(min = 3, max = 5000, message = "note must be between 3 and 5000 characters")
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
