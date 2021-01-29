package com.example.demo.sym.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.cmm.enm.Messenger;
import com.example.demo.cmm.utl.Box;
import com.example.demo.sym.service.Teacher;
import com.example.demo.sym.service.TeacherRepository;
import com.example.demo.sym.service.TeacherService;

import static com.example.demo.cmm.utl.Util.integer;

@RestController
@RequestMapping("/teachers")
@CrossOrigin(origins="*")
public class TeacherController {
    @Autowired TeacherService teacherService;
    @Autowired TeacherRepository teacherRepository;
    @Autowired Box<String> bx;

    @PostMapping("/save")
    public Messenger save(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
        return Messenger.SUCCESS;
    }

    @GetMapping("/count")
    public long count() {
        return teacherRepository.count();
    }

    @GetMapping("/existById/{id}")
    public boolean existsById(@PathVariable String id) {
        return teacherRepository.existsById(integer(id).orElse(0));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable String id) {
        return teacherRepository.findById(integer(id).orElse(0)).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/findAll")
    public Page<Teacher> findAll(@RequestBody Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @DeleteMapping("/delete")
    public Messenger delete(@RequestBody Teacher teacher) {
        teacherRepository.delete(teacher);
        return Messenger.SUCCESS;
    }

    @PostMapping("/access")
    public ResponseEntity<Teacher> access(@RequestBody Teacher teacher) {
        return teacherRepository.findById(0).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
    /**
     * 해당 교강사가 담당하는 과목의 최근 시험결과에 따른 결과반환
     * */
    @GetMapping("/page/{pageSize}/{pageNum}/subject/{subNum}/{examDate}")
    public Map<?,?> selectAllBySubject(
    		@PathVariable String pageSize, 
			@PathVariable String pageNum,
    		@PathVariable String subNum,
    		@PathVariable String examDate){
    	bx.put("pageSize", pageSize);
    	bx.put("pageNum", pageNum);
    	bx.put("subNum", subNum);
    	bx.put("examDate", examDate);
    	teacherService.selectAllBySubject(bx);
    	return null;
    }
  
} 









