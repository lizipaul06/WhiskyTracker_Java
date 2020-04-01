package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/distilleries")
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/distilleries")
    public ResponseEntity findDistilleries(){
        return new ResponseEntity(distilleryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findDistilleryById(@RequestParam(name = "id" ) Long id){
        return new ResponseEntity(distilleryRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "regions")
    public ResponseEntity findDistilleryByRegion(@RequestParam(name = "region", required=false) String region){
        if(region != null){
            return new ResponseEntity(distilleryRepository.findByRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity(distilleryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "whiskies")
    public ResponseEntity findDistilleryByWhiskyAgeGreaterThan(@RequestParam(name = "age")int age){
        return new ResponseEntity(distilleryRepository.findByWhiskiesAgeGreaterThan(age), HttpStatus.OK);
    }
}
