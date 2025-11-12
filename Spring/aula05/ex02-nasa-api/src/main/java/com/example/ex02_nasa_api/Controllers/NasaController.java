package com.example.ex02_nasa_api.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ex02_nasa_api.Service.NasaService;

@RestController
@RequestMapping("/nasa")
public class NasaController {
    private final NasaService nasaService; // pq final???

    public NasaController(NasaService nasaService) {
        this.nasaService = nasaService;
    }

    @GetMapping("/apod")
    public String getApod() {
        return nasaService.getApod();
    }
    
}
