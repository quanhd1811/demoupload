package com.example.helloworld.controller;

import com.example.helloworld.constant.ResponseStatus;
import com.example.helloworld.model.MessageResponse;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public ResponseEntity<?> toResponse(Object object){
        if(object == null){
            return ResponseEntity.ok(new MessageResponse(ResponseStatus.FAIL,"Call API Failed",null));
        }else{
            return ResponseEntity.ok(object);
        }
    }
}
