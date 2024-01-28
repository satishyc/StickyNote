package com.stickyNote.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sticky_notes_with_user")
public class NoteEntityUser extends NoteEntity{
    @Size(min = 3, max=50, message = "userName is not in the specified size range")
    @NotEmpty(message = "userName cannot be blank")
    private String userName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
