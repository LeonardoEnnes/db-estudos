package com.example.ex01_jsonplaceholder.Controller;

// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ex01_jsonplaceholder.Model.PostModel;
import com.example.ex01_jsonplaceholder.Service.JsonPlaceHolderService;


@RestController
@RequestMapping("/jph/api")
public class HomeController {
    
    private final JsonPlaceHolderService jsonPlaceHolderService;

    public HomeController(JsonPlaceHolderService jsonPlaceHolderService) {
        this.jsonPlaceHolderService = jsonPlaceHolderService;
    }

    @GetMapping("/postUnico")
    public String postUnico() {
        return jsonPlaceHolderService.getPostUnico();
    }

    @GetMapping("/postUnico/{id}")
    public String postUnicoId(@PathVariable int id) {
        return jsonPlaceHolderService.getPostUnicoId(id);
    }
    
    @GetMapping("/postObjeto/{id}")
    public PostModel postModel(@PathVariable int id) {
        return jsonPlaceHolderService.getPostModel(id);
    }
   
    @GetMapping("/posts")
    public List<PostModel> getAllPosts() {
        return jsonPlaceHolderService.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    public List<PostModel> getAllPostsById(@PathVariable int id) {
        return jsonPlaceHolderService.getAllPostsById(id);
    }
}