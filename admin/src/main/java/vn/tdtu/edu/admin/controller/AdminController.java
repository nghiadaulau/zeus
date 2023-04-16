package vn.tdtu.edu.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/utilities-color")
    public String color(Model model) {
        return "utilities-color";
    }
    @GetMapping("/utilities-border")
    public String border(Model model) {
        return "utilities-border";
    }
    @GetMapping("/utilities-animation")
    public String animation(Model model) {
        return "utilities-animation";
    }
    @GetMapping("/utilities-other")
    public String other(Model model) {
        return "utilities-other";
    }
}
