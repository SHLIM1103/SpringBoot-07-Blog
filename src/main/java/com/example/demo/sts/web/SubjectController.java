package com.example.demo.sts.web;

import static com.example.demo.cmm.utl.Util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import com.example.demo.cmm.enm.Messenger;
import com.example.demo.cmm.utl.Box;
import com.example.demo.sts.service.Subject;
import com.example.demo.sts.service.SubjectRepository;
import com.example.demo.sts.service.SubjectService;

@RequestMapping("/subjects")
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectRepository subjectRepository;
    private final SubjectService subjectService;
    private final Box<String> bx;

    @PostMapping("/save")
    public Messenger save(@RequestBody Subject subject) {
        subjectRepository.save(subject);
        return Messenger.SUCCESS;
    }
    
    @GetMapping("/count")
    public long count() {
        return subjectRepository.count();
    }
    
    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable String id) {
        return subjectRepository.existsById(integer.apply(id));
    }
    
    @GetMapping("/findById/{id}")
    public Optional<Subject> findById(@PathVariable String id) {
        return subjectRepository.findById(integer.apply(id));
    }
    
    @PostMapping("/findAll")
    public Page<Subject> findAll(@RequestBody Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }
    
    @DeleteMapping("/delete")
    public Messenger delete(@RequestBody Subject subject){
        subjectRepository.delete(subject);
        return Messenger.SUCCESS;
    }

    @GetMapping("/groupBy/{examDate}/{subNum}")
    public Map<?,?> totalScoreGroupBySubject(@PathVariable String examDate,
                                             @PathVariable String subNum) {
        bx.put("examDate", examDate);
        bx.put("subNum", subNum);
        subjectService.groupBySubject(bx);
        return null;
    }
    @GetMapping("/groupByGrade/{examDate}/{subNum}")
    public Map<?,?> groupByGrade(@PathVariable String examDate,
                                 @PathVariable String subNum) {
        bx.put("examDate", examDate);
        bx.put("subNum", subNum);
        subjectService.groupBySubject(bx);
        return null;
    }

}