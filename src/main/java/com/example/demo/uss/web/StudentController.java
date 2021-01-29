package com.example.demo.uss.web;

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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.demo.cmm.utl.Util.integer;
import static com.example.demo.cmm.utl.Util.string;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins="*")
@ConfigurationProperties(prefix = "student.list")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ManagerService managerService;
    private final SubjectService subjectService;
    private final Student student;

    @PostMapping("/save")
    public String save(@RequestBody Student student) {
        studentRepository.save(student);
        return "redirect:/login";
    }

    @GetMapping("/count")
    public long count() {
        return studentRepository.count();
    }

    @GetMapping("/existById/{id}")
    public boolean existsById(@PathVariable String id) {
        return studentRepository.existsById(integer(id).orElse(0));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Student> findById(@PathVariable String id) {
        return studentRepository.findById(integer(id).orElse(0)).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll/{pageNum}")
    public Page<Student> findAll(@PathVariable String pageNum) {
        Pageable pageable = PageRequest.of(integer(pageNum).orElse(1)-1, student.getPageSize());
        return studentRepository.findAll(pageable);
    }

    @PostMapping("/findByStudentsOrderByStuNumDesc/{pageNum}")
    public Page<Student> findByStudentsOrderByStuNumDesc(@PathVariable String pageNum) {
        Pageable pageable = PageRequest.of(integer(pageNum).orElse(1)-1, student.getPageSize());
        return studentRepository.findByStudentsOrderByStuNumDesc(pageable);
    }

    @DeleteMapping("/delete/{id}")
    public Messenger delete(@RequestBody Student student) {
        studentRepository.delete(student);
        return Messenger.SUCCESS;
    }

    @PostMapping("/login")
    public Map<?,?> login(@RequestBody Student s){
        var map = new HashMap<>();
        // Optional<Student> result = studentRepository.findById(s.getStuNum());
        Optional<Student> result = null;
        map.put("message", result!=null?"SUCCESS":"FAILURE");
        map.put("sessionUser", result);
        return map;
    }

    @GetMapping("/list/{pageSize}/{pageNum}")
    public Map<?,?> list(@PathVariable String pageSize,
    					@PathVariable String pageNum){
    	var map = new HashMap<String,String>();
    	map.put("TOTAL_COUNT", Sql.TOTAL_COUNT.toString() + Table.STUDENTS);
    	var page = new Pagination(
				Table.STUDENTS.toString(),
				integer(pageSize).orElse(20),
				integer(pageNum).orElse(1)-1, 100);
    	var map2 = new HashMap<String, Object>();
    	map2.put("list", studentService.list(page));
    	map2.put("page", page);
        return map2;
    }

    @GetMapping("/page/{pageSize}/{pageNum}/selectAll")
    public List<?> selectAll(@PathVariable String pageSize,
    					@PathVariable String pageNum){
    	var map = new HashMap<String,String>();
    	map.put("TOTAL_COUNT", Sql.TOTAL_COUNT.toString() + Table.STUDENTS);
        return studentRepository.findAll();
    }

    @PutMapping("/update")
    public Messenger update(@RequestBody Student s){
        return Messenger.SUCCESS;
    }

    @PatchMapping("/patch/{id}")
    public Messenger patch(@PathVariable String id,
                           @RequestBody Student student) {
        Student s = studentRepository.findById(integer(id).orElse(0)).orElse(student);
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