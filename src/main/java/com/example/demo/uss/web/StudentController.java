package com.example.demo.uss.web;

import static com.example.demo.cmm.utl.Util.*;

import java.util.Map;

import com.example.demo.cmm.enm.Messenger;
import com.example.demo.cmm.enm.Sql;
import com.example.demo.cmm.enm.Table;
import com.example.demo.cmm.utl.Pagination;
import com.example.demo.sts.service.SubjectService;
import com.example.demo.sym.service.ManagerService;
import com.example.demo.sym.service.TeacherService;
import com.example.demo.uss.service.Student;
import com.example.demo.uss.service.StudentRepository;
import com.example.demo.uss.service.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class StudentController {
    private final Student student;
    private final StudentService studentService;
    private final ManagerService managerService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final StudentRepository studentRepository;

    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @GetMapping("/count")
    public long count() {
        return studentRepository.count();
    }

    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable String id) {
        return studentRepository.existsById(optInteger(id).orElse(0));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Student> findById(@PathVariable String id) {
        return (studentRepository
                .findById(optInteger(id).orElse(0)).isPresent())
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll/{pageNum}")
    public Page<Student> findAll(@PathVariable String pageNum) {
        Pageable pageable = PageRequest.of(optInteger(pageNum).orElse(1) -1, student.getPageSize());
        return studentRepository.findAll(pageable);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> delete(@PathVariable String id, @RequestBody Student student) {
        if(studentRepository.findById(optInteger(id).orElse(0)).isPresent()) {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByStudentsOrderByStuNumDesc/{pageNum}")
    public Page<Student> findByStudentsOrderByStuNumDesc(@PathVariable String pageNum) {
        Pageable pageable = PageRequest.of(optInteger(pageNum).orElse(1) -1, student.getPageSize());
        return studentRepository.findByStudentsOrderByStuNumDesc(pageable);
    }

    @PostMapping("/login/{id}")
    public Map<?,?> login(@PathVariable String id, @RequestBody Student s) {
        var map = new HashMap<>();
        Optional<Student> result = studentRepository.findById(optInteger(id).orElse(0));
        if(result.isPresent()){
            map.put("message", "SUCCESS");
            map.put("sessionUser", result);
        }else{
            map.put("message", "FAILURE");
        }
        return map;
    }

    @GetMapping("/list/{pageSize}/{pageNum}")
    public Map<?,?> list(@PathVariable String pageSize, @PathVariable String pageNum) {
        var map = new HashMap<String,String>();
        map.put("count", optLongToString(studentRepository.count()).orElse("0"));
        var page = new Pagination(Table.STUDENTS.toString(),
                                optInteger(pageSize).orElse(20),
                                optInteger(pageNum).orElse(1) -1, 100);
        var map2 = new HashMap<String, Object>();
        map2.put("list", studentService.list(page));
        map2.put("page", page);
        return map2;
    }

    @GetMapping("/page/{pageSize}/{pageNum}/selectAll")
    public List<?> selectAll(@PathVariable String pageSize, @PathVariable String pageNum) {
        var map = new HashMap<String,String>();
        map.put("TOTAL_COUNT", Sql.TOTAL_COUNT.toString() + Table.STUDENTS);
        return studentRepository.findAll();
    }

    @PutMapping("/update")
    public Messenger update(@RequestBody Student s) {
        return Messenger.SUCCESS;
    }

    @PatchMapping("/patch/{id}")
    public Messenger patch(@PathVariable String id, @RequestBody Student student) {
        Student s = studentRepository.findById(optInteger(id).orElse(0)).orElse(student);
        return Messenger.SUCCESS;
    }

    @GetMapping("/insert-many/{count}")
    public String insertMany(@PathVariable String count) {
        var map = new HashMap<String,String>();
        map.put("TOTAL_COUNT", Sql.TOTAL_COUNT.toString() + Table.STUDENTS);
        if(studentRepository.count() == 0) {
            managerService.insertMany(1);
            subjectService.insertMany(5);
            studentService.insertMany(Integer.parseInt(count));
            teacherService.insertMany(5);
            //gradeService.insertMany(Integer.parseInt(count)); 나중에 추가함
        }
        map.clear();
        map.put("TOTAL_COUNT", Sql.TOTAL_COUNT.toString() + Table.STUDENTS);
        return string.apply(studentRepository.count());
    }

    @GetMapping("/find-by-gender/{gender}")
    public List<Student> findByGender(@PathVariable String gender) {
        return null; //studentService.selectByGender(gender);
    }
}