package com.example.demo.sts.web;

import java.util.Arrays;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.example.demo.cmm.enm.Messenger;
import com.example.demo.cmm.utl.Box;
import com.example.demo.sts.service.Subject;
import com.example.demo.sts.service.SubjectRepository;
import com.example.demo.sts.service.SubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequestMapping("/subjects")
@RestController
@CrossOrigin(origins = "*")
public class SubjectController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired SubjectRepository subjectRepository;
    @Autowired SubjectService subjectService;
    @Autowired Box<String> bx;

    @PostMapping("")
    public Messenger register(@RequestBody Subject s) {
        // Get the List
        List<String> g = Arrays.asList("geeks", "for", "geeks");

        // Collect the list as map by groupingBy() method
        subjectService.groupBySubject(bx);
        subjectRepository.save(s);
        return subjectRepository.count() != 0 ? Messenger.SUCCESS : Messenger.FAILURE;
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