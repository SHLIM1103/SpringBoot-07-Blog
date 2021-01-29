package com.example.demo.sts.web;

import com.example.demo.sts.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.example.demo.cmm.enm.Messenger;
import com.example.demo.cmm.utl.Box;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.example.demo.cmm.utl.Util.integer;

@RequestMapping("/subjects")
@RestController
@CrossOrigin(origins="*")
public class SubjectController {
    @Autowired SubjectService subjectService;
    @Autowired SubjectRepository subjectRepository;
    @Autowired Box<String> bx;

    @PostMapping("/save")
    public Messenger save(@RequestBody Subject subject) {
        subjectRepository.save(subject);
        return Messenger.SUCCESS;
    }

    @GetMapping("/count")
    public long count() {
        return subjectRepository.count();
    }

    @GetMapping("/existById/{id}")
    public boolean existsById(@PathVariable String id) {
        return subjectRepository.existsById(integer(id).orElse(0));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Subject> findById(@PathVariable String id) {
        return subjectRepository.findById(integer(id).orElse(0)).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/findAll")
    public Page<Subject> findAll(@RequestBody Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    @DeleteMapping("/delete")
    public Messenger delete(@RequestBody Subject subject) {
        subjectRepository.delete(subject);
        return Messenger.SUCCESS;
    }

    @GetMapping("/groupBy/{examDate}/{subNum}")
    public Map<?,?> totalScoreGroupBySubject(
    		@PathVariable String examDate,
    		@PathVariable String subNum){
    	bx.put("examDate", examDate);
    	bx.put("subNum", subNum);
    	subjectService.groupBySubject(bx);
    	return null;
    }
    @GetMapping("/groupBySubject/{examDate}/{subNum}")
    public Map<?,?> groupBySubject(
    		@PathVariable String examDate,
    		@PathVariable String subNum){
    	bx.put("examDate", examDate);
    	bx.put("subNum", subNum);
    	subjectService.groupBySubject(bx);
    	return null;
    }

}