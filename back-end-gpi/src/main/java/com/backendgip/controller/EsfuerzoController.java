package com.backendgip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.model.Esfuerzo;

import com.backendgip.service.EsfuerzoService;



@RestController
@RequestMapping("/api")
public class EsfuerzoController {

  
    @Autowired
    private EsfuerzoService esfuerzoService;

    
    @GetMapping({"/esfuerzo"})
    public List<Esfuerzo> gEsfuerzos(){
        return this.esfuerzoService.gEsfuerzos() ;
    }
}

