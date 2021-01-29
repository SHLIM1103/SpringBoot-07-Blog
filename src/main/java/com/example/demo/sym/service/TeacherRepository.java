package com.example.demo.sym.service;

import com.example.demo.cmm.enm.Messenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.demo.cmm.utl.Util.integer;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

}
