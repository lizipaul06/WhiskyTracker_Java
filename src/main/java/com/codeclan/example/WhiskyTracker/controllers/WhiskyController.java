package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/whiskies")
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping
    public ResponseEntity findWhiskies(){
        return new ResponseEntity(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findWhiskyById(@RequestParam(name = "id" ) Long id){
        return new ResponseEntity(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/year")
    public ResponseEntity findWhiskeyFilterByYear(@RequestParam(name = "year") int year){

            return new ResponseEntity(whiskyRepository.findByYear(year), HttpStatus.OK);

    }

    @GetMapping(value = "/distilleries")
    public  ResponseEntity findWhiskeyFilterByDistilleryNameAndYear(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "year", required = false) int year)
    {
        return new ResponseEntity(whiskyRepository.findByDistilleryNameAndYear(name, year),HttpStatus.OK);


    }

    @GetMapping(value ="/regions")
    public ResponseEntity findWhiskeyByRegion(
            @RequestParam(name = "region", required = false) String region){
                if(region != null){
                    return new ResponseEntity(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
                }

                return new ResponseEntity(whiskyRepository.findAll(), HttpStatus.OK);
    }


}
