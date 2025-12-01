package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Composition;
import com.example.demospringboot.repository.CompositionRepository;
import java.util.List;

@Service
public class CompositionService{
    @Autowired
    private CompositionRepository compositionRepository;

    public List<Composition> getAllComposition() {
        return compositionRepository.findAll();
    }
    public Composition addComposition(Composition obj) {
        Long id = null;
        obj.setId(id);
        return compositionRepository.save(obj);
    }

    public Composition getCompositionById(long id) {
        return compositionRepository.findById(id).orElse(null);
    }

    public Composition updateComposition(long id, Composition obj) {
        obj.setId(id);
        return compositionRepository.save(obj); 
    }

    public void deleteComposition(long id) {
        compositionRepository.deleteById(id);
    }
}