package com.iaccarino.testNicMa.service;

import com.iaccarino.testNicMa.model.Post;
import com.iaccarino.testNicMa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Gestione dello strato Service
     Implementazione logiche business con relativi metodi,
     Injection di postRepository per operazioni Crud
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void salvaPost(Post post){
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {

        return postRepository.findAll();
    }
}
