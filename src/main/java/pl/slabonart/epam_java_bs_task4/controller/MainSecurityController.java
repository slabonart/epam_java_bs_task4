package pl.slabonart.epam_java_bs_task4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainSecurityController {

    @GetMapping("/info")
    public String info() {
        return "info";
    }
}
