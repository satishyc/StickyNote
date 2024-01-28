package com.stickyNote.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sticky_notes_with_user")
public class NoteEntityUser {
    @Id
    @Hidden
    private  String id;
    @Size(min = 3, max=5000, message = "note is not in the specified size range")
    @NotEmpty(message = "Note cannot be blank")
    private String note;
    @Size(min = 3, max=50, message = "userName is not in the specified size range")
    @NotEmpty(message = "userName cannot be blank")
    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
