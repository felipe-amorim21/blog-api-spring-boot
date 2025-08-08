package br.com.felipe.blog.service;

import br.com.felipe.blog.dto.PostRequestDTO;
import br.com.felipe.blog.dto.PostResponseDTO;
import br.com.felipe.blog.entity.Post;
import br.com.felipe.blog.exceptions.ResourceNotFoundException;
import br.com.felipe.blog.mapper.PostMapper;
import br.com.felipe.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public PostResponseDTO save (PostRequestDTO postDTO){
        Post post = postMapper.toEntity(postDTO);
        Post savedPost = postRepository.save(post);
        return postMapper.toResponseDTO(savedPost);
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

    public void deleteById(Long id){
        if(!postRepository.existsById(id)){
            throw new ResourceNotFoundException("Post de id " + id + " não encontrado");
        }
        postRepository.deleteById(id);
    }
}
