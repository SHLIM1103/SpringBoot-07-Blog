package com.example.demo.cop.bbs.web;

import com.example.demo.cmm.enm.Messenger;
import com.example.demo.cop.bbs.service.Reply;
import com.example.demo.cop.bbs.service.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.demo.cmm.utl.Util.integer;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/replies")
public class ReplyController {
    @Autowired ReplyRepository replyRepository;

    @PostMapping("/save")
    public Messenger save(@RequestBody Reply reply) {
        replyRepository.save(reply);
        return Messenger.SUCCESS;
    }

    @GetMapping("/count")
    public long count() {
        return replyRepository.count();
    }

    @GetMapping("/existById/{id}")
    public boolean existsById(@PathVariable String id) {
        return replyRepository.existsById(integer(id).orElse(0));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Reply> findById(@PathVariable String id) {
        return replyRepository.findById(integer(id).orElse(0)).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/findAll")
    public Page<Reply> findAll(@RequestBody Pageable pageable) {
        return replyRepository.findAll(pageable);
    }

    @DeleteMapping("/delete")
    public Messenger delete(@RequestBody Reply reply) {
        replyRepository.delete(reply);
        return Messenger.SUCCESS;
    }
}