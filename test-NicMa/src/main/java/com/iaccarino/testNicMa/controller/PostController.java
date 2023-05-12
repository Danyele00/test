package com.iaccarino.testNicMa.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iaccarino.testNicMa.model.Post;
import com.iaccarino.testNicMa.repository.PostRepository;

import com.iaccarino.testNicMa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
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

/*
Gestione dello strato Controller
    Sei end point per l'inserimento di dati (json) nel database
    - GET /  - home page, tabella con tutti i post salvati nel database in formato JSON.
    - GET /import - esegue una chiamata REST all'indirizzo https://jsonplaceholder.typicode.com/posts per recuperare una lista di post e salvarli nel database.
    - GET /new - inserimento nuovo elemento da form
    - POST /new - crea un nuovo elemento post e lo salva nel database
    - POST /saveCustomJson - inserimento elemento nel database da una stringa (json)
    - GET /importJsonFile - inserimento elemento nel database da un file .json
    - GET /posts - restituisce tutti i post salvati nel database in formato JSON.
 */
@Controller
public class PostController {


    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "home";
    }

    @GetMapping("/import")
    public String importPosts() throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Post[]> response = restTemplate.getForEntity(
                "https://jsonplaceholder.typicode.com/posts",
                Post[].class);
        Post[] posts = response.getBody();

        for (Post post : posts) {
            postService.salvaPost(post);
        }

        return "redirect:/";
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
        postService.salvaPost(post);
        return "redirect:/";
    }


    //Inserimento a DB da String
    @PostMapping("/saveCustomJson")
    public String salvaPostJson(@ModelAttribute Post post) throws IOException {

        String postJson = """
                {
                  "userId": 1,
                  "id": null,
                  "title": "post passato da Stringa con id null",
                  "body": "post passato da Stringa con id null"
                }""";

        ObjectMapper objectMapper = new ObjectMapper();
        post = objectMapper.readValue(postJson, Post.class);

        postService.salvaPost(post);
        return "redirect:/";
    }

    //Inserimento a DB da file .json
    @GetMapping("/importJsonFile")
    public String salvaPostJsonFile(@ModelAttribute Post post) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Post> posts= objectMapper.readValue(new File("C:\\workspaces Intellij\\testNicMa\\test-NicMa\\src\\main\\resources\\static\\postJsonFile.json"), new TypeReference<List<Post>>(){});

        for(Post elemento : posts)
            postService.salvaPost(elemento);

        return "redirect:/";
    }





}

