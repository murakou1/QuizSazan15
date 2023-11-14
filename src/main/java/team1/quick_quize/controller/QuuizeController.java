package team1.quick_quize.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuuizeController {

  @GetMapping("/home")
  public String home(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    model.addAttribute("loginUser", loginUser);
    return "home.html";
  }

  @GetMapping("/wait")
  public String wait(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    model.addAttribute("loginUser", loginUser);
    return "wait.html";
  }

}
