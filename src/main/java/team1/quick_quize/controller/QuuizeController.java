package team1.quick_quize.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team1.quick_quize.model.Users;
import team1.quick_quize.model.UsersMapper;

@Controller
public class QuuizeController {

  @Autowired
  UsersMapper usersMapper;

  @GetMapping("/home")
  public String home(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    model.addAttribute("loginUser", loginUser);
    return "home.html";
  }

  @GetMapping("/wait")
  public String wait(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    Users User = new Users();
    User.setUserName(loginUser);
    User.setPoint(0);

    usersMapper.insertUser(User);
    model.addAttribute("loginUser", loginUser);
    return "wait.html";
  }

  @GetMapping("/quize")
  public String quize() {
    return "quize.html";
  }

  @GetMapping("/answer")
  public String answer() {
    return "answer.html";
  }

  @GetMapping("/result")
  public String result() {
    return "result.html";
  }
}
