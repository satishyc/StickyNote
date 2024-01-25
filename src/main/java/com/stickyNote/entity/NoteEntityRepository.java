package com.stickyNote.entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface NoteEntityRepository extends MongoRepository<NoteEntity,String> {

    List<NoteEntity> findAll();
    void deleteById(String id);

    Optional<NoteEntity> findById(String id);
    NoteEntity findByNote(String note);
}
