package com.stickyNote.service;

import com.stickyNote.entity.NoteEntityUser;
import com.stickyNote.entity.NoteEntityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteEntityUserService {
    @Autowired
    NoteEntityUserRepository repository;
    public void saveEntityDetails(NoteEntityUser entity){
        List<NoteEntityUser> list = repository.findByUserName(entity.getUserName());
        for(NoteEntityUser entityUser : list){
            if(entityUser.getNote().equals(entity.getNote())){
                throw new IllegalArgumentException("Given Note "+entity.getNote()+" is already" +
                        " present under this UserName "+entity.getUserName());
            }
        }
        repository.save(entity);
    }
    public NoteEntityUser getDetailsById(String id){
        Optional<NoteEntityUser> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new IllegalArgumentException("Given Id "+id+" not present is present in records");
        }
        return optional.get();
    }
    public void deleteById(NoteEntityUser entity){
        Optional<NoteEntityUser> optional = repository.findById(entity.getId());
        if(optional.isEmpty()){
            throw new IllegalArgumentException("Given Id "+entity.getId()+" not present is present in records");
        }
        NoteEntityUser userEntity = optional.get();
        if(!userEntity.getUserName().equals(entity.getUserName())){
            throw new IllegalArgumentException("Given UserName "+entity.getUserName()+" not matching with given id");

        }
        repository.deleteById(entity.getId());
    }
    public List<NoteEntityUser> findByUserNameDetails(String userName){
        return repository.findByUserName(userName);
    }
    public List<NoteEntityUser> findAllDetails(){
        return repository.findAll();
    }

    public void updateDetails(NoteEntityUser entity){
        List<NoteEntityUser> list = repository.findByUserName(entity.getUserName());
        if(list.isEmpty()){
            throw new IllegalArgumentException("Given Note with ID "+entity.getId()+
                    " is not present in our records");
        }
        for(NoteEntityUser entityUser : list){
            if(entityUser.getNote().equals(entity.getNote())){
                throw new IllegalArgumentException("Given Note "+entity.getNote()+" is already" +
                        " present under this UserName "+entity.getUserName());
            }
        }
        repository.save(entity);
    }
}
