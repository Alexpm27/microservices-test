package com.example.webhookswithspring.contraller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook/userCreated")
public class WebHookController {

    @PostMapping
    public ResponseEntity<String> printEvent(@RequestBody String requestBody){
        System.out.print("\n\nWebHook: " + requestBody);
        return new ResponseEntity<String>(requestBody, HttpStatus.OK);
    }

}
