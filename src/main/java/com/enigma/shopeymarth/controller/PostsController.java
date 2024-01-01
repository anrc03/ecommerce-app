package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.entity.Posts;
import com.enigma.shopeymarth.service.impl.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPosts() {
        return postsService.getAllPosts();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPostsById(@PathVariable Long id) {
        return postsService.getPostsById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPosts(Posts posts) {
        return postsService.createPosts(posts);
    }
}
