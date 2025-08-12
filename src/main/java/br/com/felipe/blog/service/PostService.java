package br.com.felipe.blog.service;

import br.com.felipe.blog.dto.PostRequestDTO;
import br.com.felipe.blog.dto.PostResponseDTO;
import br.com.felipe.blog.entity.Post;
import br.com.felipe.blog.exceptions.ResourceNotFoundException;
import br.com.felipe.blog.mapper.PostMapper;
import br.com.felipe.blog.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Transactional
    public PostResponseDTO save(PostRequestDTO postDTO){
        Post post = postMapper.toEntity(postDTO);
        Post savedPost = postRepository.save(post);
        return postMapper.toResponseDTO(savedPost);
    }

    public PostResponseDTO update(Long id, PostRequestDTO postRequestDTO){
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post de id " + id + " não encontrado"));

        existingPost.setTitle(postRequestDTO.getTitle());
        existingPost.setContent(postRequestDTO.getContent());
        Post updatedPost = postRepository.save(existingPost);
        return postMapper.toResponseDTO(updatedPost);
    }

    public PostResponseDTO patch(Long id, PostRequestDTO postRequestDTO){
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post de id " + id + " não encontrado"));

        if (postRequestDTO.getTitle() != null) existingPost.setTitle(postRequestDTO.getTitle());
        if (postRequestDTO.getContent() != null) existingPost.setContent(postRequestDTO.getContent());

        Post updatedPost = postRepository.save(existingPost);
        return postMapper.toResponseDTO(updatedPost);
    }

    public PostResponseDTO findById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post de id " + id + " não encontrado"));
        return postMapper.toResponseDTO(post);
    }

    public List<PostResponseDTO> findAll(){
        List<Post> post =  postRepository.findAll();
        return post.stream()
                .map(postMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public void deleteById(Long id){
        if(!postRepository.existsById(id)){
            throw new ResourceNotFoundException("Post de id " + id + " não encontrado");
        }
        postRepository.deleteById(id);
    }
}
