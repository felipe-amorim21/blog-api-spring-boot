package br.com.felipe.blog.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.felipe.blog.dto.CommentRequestDTO;
import br.com.felipe.blog.dto.CommentResponseDTO;
import br.com.felipe.blog.dto.CommentUpdateRequestDTO;
import br.com.felipe.blog.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO createdComment = commentService.save(commentRequestDTO);

        URI commentUri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdComment.getId())
                        .toUri();

        return ResponseEntity.created(commentUri).body(createdComment);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDTO>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> findCommentById(@PathVariable Long id) {
        CommentResponseDTO comment = commentService.findById(id);
        return ResponseEntity.ok(comment);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<CommentResponseDTO> update(@PathVariable Long id, @RequestBody CommentUpdateRequestDTO commentUpdateRequestDTO) {
        CommentResponseDTO updateComment = commentService.update(id, commentUpdateRequestDTO);
        return ResponseEntity.ok(updateComment);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id) {
        commentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
