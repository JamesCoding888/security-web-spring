package com.example.demo.controller.errorhandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyControllerResponseStatus {
	
    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String test() {    	    
        return "Test successful";
    }

    @GetMapping("/statuscode")
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public String error() {
    	return HttpStatus.BAD_GATEWAY.toString();
    }
}
