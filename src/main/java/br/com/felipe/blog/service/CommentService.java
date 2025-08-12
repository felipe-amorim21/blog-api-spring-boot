package br.com.felipe.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.felipe.blog.dto.CommentRequestDTO;
import br.com.felipe.blog.dto.CommentResponseDTO;
import br.com.felipe.blog.dto.CommentUpdateRequestDTO;
import br.com.felipe.blog.entity.Comment;
import br.com.felipe.blog.entity.Post;
import br.com.felipe.blog.exceptions.ResourceNotFoundException;
import br.com.felipe.blog.mapper.CommentMapper;
import br.com.felipe.blog.repository.CommentRepository;
import br.com.felipe.blog.repository.PostRepository;
import jakarta.transaction.Transactional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper,
            PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postRepository = postRepository;
    }

    @Transactional
    public CommentResponseDTO save(CommentRequestDTO commentDTO) {
        Long postId = commentDTO.getPostId();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post de id " + postId + " não encontrado. Não é possível criar o comentário."));
        Comment comment = commentMapper.toEntity(commentDTO, post);
        Comment savedcomment = commentRepository.save(comment);
        return commentMapper.toResponseDTO(savedcomment);
    }

    public List<CommentResponseDTO> findAll() {
        List<Comment> comment = commentRepository.findAll();
        return comment.stream()
                .map(commentMapper::toResponseDTO)
                .toList();
    }

    public CommentResponseDTO findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário de id " + id + " não encontrado."));
        return commentMapper.toResponseDTO(comment);
    }

    public CommentResponseDTO update(Long id, CommentUpdateRequestDTO commentRequestDTO) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário de id " + id + " não encontrado"));

        existingComment.setContent(commentRequestDTO.getContent());
        Comment updatedComment = commentRepository.save(existingComment);
        return commentMapper.toResponseDTO(updatedComment);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comentário de id " + id + " não encontrado");
        }
        commentRepository.deleteById(id);
    }
}
