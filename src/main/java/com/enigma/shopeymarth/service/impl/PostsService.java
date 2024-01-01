package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.entity.Customer;
import com.enigma.shopeymarth.entity.Posts;
import com.enigma.shopeymarth.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostsService {

    @Value("https://jsonplaceholder.typicode.com/posts")
    private String BASE_URL;

    private final RestTemplate restTemplate;
    private final PostsRepository postsRepository;

    public ResponseEntity<List<Posts>> getAllPosts() {
        List<Posts> posts = postsRepository.findAll();
        Posts[] resp = restTemplate.getForEntity(BASE_URL, Posts[].class).getBody();
        if (resp != null) return ResponseEntity.ok(Stream.concat(posts.stream(), Stream.of(resp)).toList()) ;
        return null;
    }

    public ResponseEntity<?> getPostsById(Long id) {
        if (id > 100) return ResponseEntity.ok(postsRepository.findById(id.intValue()).orElse(null));
        Posts resp = restTemplate.getForEntity(BASE_URL + "/" + id, Posts.class).getBody();
        Optional<Posts> dbResp = postsRepository.findById(id.intValue());
        if (resp != null && dbResp.isPresent()) return ResponseEntity.ok(List.of(resp, dbResp.get()));
        if (resp != null) return ResponseEntity.ok(resp);
        if (dbResp.isPresent()) return ResponseEntity.ok(dbResp.get());
        return null;
    }

    public ResponseEntity<String> createPosts(Posts posts) {
        postsRepository.save(posts);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Posts> requestEntity = new HttpEntity<>(posts, headers);
        return responseMethod(restTemplate.postForEntity(BASE_URL, requestEntity, String.class), "Failed to create data");
    }

    private ResponseEntity<String> responseMethod(ResponseEntity<String> restTemplate, String message) {
        ResponseEntity<String> responseEntity = restTemplate;
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            return ResponseEntity.ok(responseBody);
        }
        return ResponseEntity.status(responseEntity.getStatusCode()).body(message);
    }

}
