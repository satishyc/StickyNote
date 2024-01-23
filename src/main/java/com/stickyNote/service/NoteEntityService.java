package com.stickyNote.service;

import com.stickyNote.entity.NoteEntity;
import com.stickyNote.entity.NoteEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteEntityService {
    @Autowired
    NoteEntityRepository repository;

    public void saveNoteDetails(NoteEntity entity){
        repository.save(entity);
    }
    public List<NoteEntity> getAllStickyDetails(){
        return repository.findAll();
    }
    public void deleteById(String id){
        Optional<NoteEntity> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new IllegalArgumentException("Given Id "+id+" not present is present in records");
        }
        repository.deleteById(id);
    }
    public void updateNote(NoteEntity noteEntity){
        Optional<NoteEntity> optional = repository.findById(noteEntity.getId());
        if(optional.isEmpty()){
            throw new IllegalArgumentException("Given Id "+noteEntity.getId()+" not present is present in records");
        }
        repository.save(noteEntity);
    }
}
