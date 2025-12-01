package com.example.demospringboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Staff;
import com.example.demospringboot.repository.StaffRepository;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff addStaff(Staff obj) {
        return staffRepository.save(obj);
    }

    public Staff getStaffById(long id) {
        return staffRepository.findById(id).orElse(null);
    }

    public Staff updateStaff(long id, Staff obj) {
        return staffRepository.save(obj);
    }

    public void deleteStaff(long id) {
        staffRepository.deleteById(id);
    }

    public Staff findStaff(String email, String password) {
        return staffRepository.findByEmailAndPassword(email, password);
    }
}