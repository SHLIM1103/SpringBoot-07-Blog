package com.example.demo.sts.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.cmm.enm.Messenger;
import com.example.demo.sts.service.Grade;
import com.example.demo.sts.service.GradeRepository;
import com.example.demo.sts.service.GradeService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.example.demo.cmm.utl.Util.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/grades")
public class GradeController {
    @Autowired GradeService gradeService;
    @Autowired GradeRepository gradeRepository;

    @PostMapping("/save")
    public Messenger save(@RequestBody Grade grade) {
        gradeRepository.save(grade);
        return Messenger.SUCCESS;
    }

    @GetMapping("/count")
    public long count() {
        return gradeRepository.count();
    }

    @GetMapping("/existById/{id}")
    public boolean existsById(@PathVariable String id) {
        return gradeRepository.existsById(integer(id).orElse(0));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Grade> findById(@PathVariable String id) {
        return gradeRepository.findById(integer(id).orElse(0)).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
    /*
    @PostMapping("/findAll")
    public Page<Grade> findAll(@RequestBody Pageable pageable) {
        return gradeRepository.findAll(pageable);
    }
    */
    @DeleteMapping("/delete")
    public Messenger delete(@RequestBody Grade grade) {
        gradeRepository.delete(grade);
        return Messenger.SUCCESS;
    }
}