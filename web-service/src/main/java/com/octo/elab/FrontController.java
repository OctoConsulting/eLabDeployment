package com.octo.elab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/elab")
public class FrontController {
    Logger logger = LoggerFactory.getLogger(FrontController.class);

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    WorkerConfig workerConfig;

    @GetMapping
    public String hello() {
        logger.info("Front web was called");
        
        return "Message from worker (" + workerConfig.getURL() +") is : " + restTemplate.getForObject(workerConfig.getURL(), String.class, "");
    }
    
    @RequestMapping(value = "/cases", method = RequestMethod.GET)
    public ResponseEntity<String> getCases() { 
        logger.info("Front web was called");
        System.out.println("getCases");
        return restTemplate.getForEntity(workerConfig.getURL()+"/cases", String.class, "");
    }

}
