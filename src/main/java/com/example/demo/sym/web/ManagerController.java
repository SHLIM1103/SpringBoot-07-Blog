package com.example.demo.sym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.cmm.utl.Util.*;
import com.example.demo.cmm.enm.Messenger;
import com.example.demo.sym.service.Manager;
import com.example.demo.sym.service.ManagerRepository;

@RestController
@RequestMapping("/managers")
@CrossOrigin(origins="*")
public class ManagerController {
    @Autowired ManagerRepository managerRepository;
    
    @PostMapping("/save")
    public Messenger save(@RequestBody Manager manager) {
        managerRepository.save(manager);
        return Messenger.SUCCESS;
    }

    @GetMapping("/count")
    public long count() {
        return managerRepository.count();
    }

    @GetMapping("/existById/{id}")
    public boolean existsById(@PathVariable String id) {
        return managerRepository.existsById(integer(id).orElse(0));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Manager> findById(@PathVariable String id) {
        return managerRepository.findById(integer(id).orElse(0)).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/findAll")
    public Page<Manager> findAll(@RequestBody Pageable pageable) {
        return managerRepository.findAll(pageable);
    }

    @DeleteMapping("/delete")
    public Messenger delete(@RequestBody Manager manager) {
        managerRepository.delete(manager);
        return Messenger.SUCCESS;
    }

    @PostMapping("/access")
    public ResponseEntity<Manager> access(@RequestBody Manager manager) {
        return managerRepository.findById(0).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

}