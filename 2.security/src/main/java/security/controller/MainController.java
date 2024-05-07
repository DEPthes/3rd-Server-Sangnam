package security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
    @GetMapping("api1")
    public ResponseEntity api1(){
        return new ResponseEntity<>("api1 입니다", HttpStatus.OK);
    }

    @GetMapping("api2")
    public ResponseEntity api2(){
        return new ResponseEntity<>("api2 입니다", HttpStatus.OK);
    }
}
