package edu.mum.cs544.wind;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @RequestMapping("/")
    public String getMyName() {
        return "LOGIN SUCCESSFULL";
    }
}
