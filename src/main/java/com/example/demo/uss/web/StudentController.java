package com.example.demo.uss.web;

import static com.example.demo.cmm.utl.Util.*;

import java.util.Map;

import com.example.demo.cmm.enm.Messenger;
import com.example.demo.cmm.enm.Sql;
import com.example.demo.cmm.enm.Table;
import com.example.demo.cmm.utl.Pagination;
import com.example.demo.sts.service.GradeService;
import com.example.demo.sts.service.SubjectService;
import com.example.demo.sym.service.ManagerService;
import com.example.demo.sym.service.TeacherService;
import com.example.demo.uss.service.Student;
import com.example.demo.uss.service.StudentRepository;
import com.example.demo.uss.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired StudentService studentService;
    @Autowired ManagerService managerService;
    @Autowired TeacherService teacherService;
    @Autowired SubjectService subjectService;
    @Autowired GradeService gradeService;
    @Autowired StudentRepository studentRepository;

    @PostMapping("")
    public String register(@RequestBody Student s) {
        studentRepository.save(s);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public Map<?,?> login(@RequestBody Student s) {
        var map = new HashMap<>();
//      Optional<Student> result = studentRepository.findById(s.getStuNum());
        Optional<Student> result = null;
        map.put("message", result != null ? "SUCCESS" : "FAILURE");
        map.put("sessionUser", result);
        return map;
    }

    /*
    @GetMapping("/{userid}")
    public Student profile(@PathVariable String userid) {
        return studentMapper.selectById(userid);
    }
    */

    @GetMapping("/list/{pageSize}/{pageNum}")
    public Map<?,?> list(@PathVariable String pageSize,
                         @PathVariable String pageNum) {
        logger.info("Students List Execute ...");
        var map = new HashMap<String, String>();
        map.put("TOTAL_COUNT", Sql.TOTAL_COUNT.toString() + Table.STUDENTS);
        var page = new Pagination(Table.STUDENTS.toString(),
                integer.apply(pageSize),
                integer.apply(pageNum),
                100);
        var map2 = new HashMap<String, Object>();
        map2.put("list", studentService.list(page));
        map2.put("page", page);
        return map2;
    }

    @GetMapping("/page/{pageSize}/{pageNum}/selectAll")
    public List<?> selectAll(@PathVariable String pageSize,
                             @PathVariable String pageNum) {
        logger.info("Students List Execute ...");
        var map = new HashMap<String,String>();
        map.put("TOTAL_COUNT", Sql.TOTAL_COUNT.toString() + Table.STUDENTS);
        return studentRepository.findAll();
    }

    @PutMapping("")
    public Messenger update(@RequestBody Student s) {
        return Messenger.SUCCESS;
    }

    @DeleteMapping("")
    public Messenger delete(@RequestBody Student s) {
        logger.info("Students Deleted Execute ...");
        studentRepository.delete(s);
        return Messenger.SUCCESS;
    }

    @GetMapping("/insert-many/{count}")
    public String insertMany(@PathVariable String count) {
        logger.info(String.format("Insert %s Students ...", count));
        var map = new HashMap<String, String>();
        map.put("TOTAL_COUNT", Sql.TOTAL_COUNT.toString() + Table.STUDENTS);
        if(studentRepository.count() == 0) {
            managerService.insertMany(1);
            teacherService.insertMany(5);
            subjectService.insertMany(5);
            studentService.insertMany(Integer.parseInt(count));
//    	    gradeService.insertMany(Integer.parseInt(count));
        }
        map.clear();
        map.put("TOTAL_COUNT", Sql.TOTAL_COUNT.toString() + Table.STUDENTS);
        return string.apply(studentRepository.count());
    }

    @GetMapping("/count")
    public String count() {
        logger.info(String.format("Count Students ..."));
        var map = new HashMap<String, String>();
        map.put("TOTAL_COUNT", Sql.TOTAL_COUNT.toString() + Table.STUDENTS);
        return string.apply(studentRepository.count());
    }

    @GetMapping("/find-by-gender/{gender}")
    public List<Student> findByGender(@PathVariable String gender) {
        logger.info(String.format("Find By %s from Students ...", gender));
        return null; // studentService.selectByGender(gender);
    }
}