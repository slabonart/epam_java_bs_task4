package pl.slabonart.jabs_4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainSecurityController {

    @GetMapping("/info")
    public String info() {
        return "info";
    }
}
