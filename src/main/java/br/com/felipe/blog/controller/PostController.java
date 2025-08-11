package br.com.felipe.blog.controller;

import br.com.felipe.blog.dto.PostRequestDTO;
import br.com.felipe.blog.dto.PostResponseDTO;
import br.com.felipe.blog.entity.Post;
import br.com.felipe.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> save(@RequestBody PostRequestDTO postRequestDTO) {
        PostResponseDTO createdPost = postService.save(postRequestDTO);

         URI postUri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdPost.getId())
                        .toUri();

        return ResponseEntity.created(postUri).body(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> update(@PathVariable Long id, @RequestBody PostRequestDTO postRequestDTO){
        PostResponseDTO updatedPost = postService.update(id, postRequestDTO);
        return ResponseEntity.ok(updatedPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> findPostById(@PathVariable Long id) {
        PostResponseDTO post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id) {
        postService.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }


}
