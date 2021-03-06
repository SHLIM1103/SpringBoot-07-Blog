package com.example.demo.cop.bbs.web;

import static com.example.demo.cmm.utl.Util.*;

import com.example.demo.cmm.enm.Messenger;
import com.example.demo.cop.bbs.service.Article;
import com.example.demo.cop.bbs.service.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleRepository articleRepository;

    @PostMapping("/save")
    public Messenger save(@RequestBody Article article) {
        articleRepository.save(article);
        return Messenger.SUCCESS;
    }

    @GetMapping("/count")
    public long count() {
        return articleRepository.count();
    }

    @GetMapping("/existById/{id}")
    public boolean existById(@PathVariable String id) {
        return articleRepository.existsById(integer.apply(id));
    }

    @GetMapping("/findById/{id}")
    public Optional<Article> findById(@PathVariable String id) {
        return articleRepository.findById(integer.apply(id));
    }

    @PostMapping("/findAll")
    public Page<Article> findAll(@RequestBody Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @DeleteMapping("/delete")
    public Messenger delete(@RequestBody Article article) {
        articleRepository.delete(article);
        return Messenger.SUCCESS;
    }
}