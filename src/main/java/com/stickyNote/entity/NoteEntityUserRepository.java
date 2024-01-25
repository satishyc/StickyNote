package com.stickyNote.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NoteEntityUserRepository extends MongoRepository<NoteEntityUser,String> {
    List<NoteEntityUser> findByUserName(String userName);
    Optional<NoteEntityUser> findById(String id);
    void deleteById(String id);
    List<NoteEntityUser> findAll();
}
