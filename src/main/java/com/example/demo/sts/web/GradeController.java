package com.example.demo.sts.web;

import static com.example.demo.cmm.utl.Util.*;

import com.example.demo.cmm.enm.Sql;
import com.example.demo.cmm.enm.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.demo.cmm.enm.Messenger;
import com.example.demo.sts.service.Grade;
import com.example.demo.sts.service.GradeRepository;
import com.example.demo.sts.service.GradeService;

import java.util.HashMap;
import java.util.Optional;

@RequestMapping("/grades")
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;
    private final GradeRepository gradeRepository;

    @PostMapping("/save")
    public Messenger save(@RequestBody Grade grade) {
        gradeRepository.save(grade);
        return Messenger.SUCCESS;
    }

    @GetMapping("/count")
    public long count() {
        return gradeRepository.count();
    }

    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable String id) {
        return gradeRepository.existsById(integer.apply(id));
    }

    @GetMapping("/findById/{id}")
    public Optional<Grade > findById(@PathVariable String id) {
        return gradeRepository.findById(integer.apply(id));
    }
    /*
    @PostMapping("/findAll")
    public Page<Grade> findAll(@RequestBody Pageable pageable) {
        return gradeRepository.findAll(pageable);
    } */

    @DeleteMapping("/delete")
    public Messenger delete(@RequestBody Grade  grade){
        gradeRepository.delete(grade);
        return Messenger.SUCCESS;
    }
}