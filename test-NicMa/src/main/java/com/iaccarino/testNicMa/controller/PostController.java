package com.iaccarino.testNicMa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iaccarino.testNicMa.model.Post;
import com.iaccarino.testNicMa.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class PostController {

    @Autowired
    private PostRepository postRepository;


    //Lista di tutti i post
    @GetMapping("/posts")
    public String home(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "home";
    }

    //Importa una lista da url e inserisce nel DB
    @PostMapping("/import")
    public String importPosts() throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Post[]> response = restTemplate.getForEntity(
                "https://jsonplaceholder.typicode.com/posts",
                Post[].class);
        Post[] posts = response.getBody();

        for (Post post : posts) {
            postRepository.save(post);
        }

        return "redirect:/posts";
    }

    //Creazione elemento dal form
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("post", new Post());
        return "form";
    }

    //Inserimento a DB dal Model
    @PostMapping("/new")
    public String salvaPost(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/posts";
    }


    //Inserimento a DB da String
    @PostMapping("/saveCustomJson")
    public String salvaPostJson(@ModelAttribute Post post) throws IOException {

        String postJson = """
                {
                  "userId": 1,
                  "id": 1,
                  "title": "post passato da Stringa",
                  "body": "post passato da Stringa"
                }""";

        ObjectMapper objectMapper = new ObjectMapper();
        post = objectMapper.readValue(postJson, Post.class);

        postRepository.save(post);
        return "redirect:/posts";
    }

    //Inserimento a DB da file .json
    @PostMapping("/importCustomJsonFile")
    public String salvaPostJsonFile(@ModelAttribute Post post) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        post = objectMapper.readValue(new File("src/main/resources/static/postJsonFile.json"), Post.class);
        postRepository.save(post);
        return "redirect:/posts";
    }





}

