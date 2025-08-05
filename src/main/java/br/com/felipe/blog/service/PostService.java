package br.com.felipe.blog.service;

import br.com.felipe.blog.entity.Post;
import br.com.felipe.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save (Post post){
        return postRepository.save(post);
    }

    public Post findById(Long id){
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public void deleteById(Long id){
        postRepository.deleteById(id);
    }
}
