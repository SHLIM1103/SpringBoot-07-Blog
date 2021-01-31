package com.example.demo.sym.web;

import com.example.demo.sym.service.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.cmm.enm.Messenger;
import com.example.demo.sym.service.ManagerRepository;
import com.example.demo.sym.service.ManagerService;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/managers")
public class ManagerController {
    @Autowired ManagerService managerService;
    @Autowired ManagerRepository managerRepository;

    @PostMapping("")
    public Messenger register(@RequestBody Manager manager) {
        managerRepository.save(manager);
        return Messenger.SUCCESS;
    }

    @PostMapping("/access")
    public Optional<Manager> access(@RequestBody Manager manager) {
        return managerRepository.findById(0);
    }
}