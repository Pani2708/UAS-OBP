package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.DetailMenu;
import com.example.demospringboot.repository.DetailMenuRepository;
import java.util.List;

@Service
public class DetailMenuService{
    @Autowired
    private DetailMenuRepository detailMenuRepository;

    public List<DetailMenu> getAllDetailMenu() {
        return detailMenuRepository.findAll();
    }
    public DetailMenu addDetailMenu(DetailMenu obj) {
        Long id = null;
        obj.setId(id);
        return detailMenuRepository.save(obj);
    }

    public DetailMenu getDetailMenuById(long id) {
        return detailMenuRepository.findById(id).orElse(null);
    }

    public DetailMenu updateDetailMenu(long id, DetailMenu obj) {
        obj.setId(id);
        return detailMenuRepository.save(obj);
    }

    public void deleteDetailMenu(long id) {
        detailMenuRepository.deleteById(id);
    }
}