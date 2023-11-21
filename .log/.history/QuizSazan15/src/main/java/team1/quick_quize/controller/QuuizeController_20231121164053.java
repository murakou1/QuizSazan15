package team1.quick_quize.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import team1.quick_quize.model.Users;
import team1.quick_quize.model.UsersMapper;
import team1.quick_quize.service.AsyncPrintUsers;

@Controller
public class QuuizeController {

  @Autowired
  UsersMapper usersMapper;

  @Autowired
  private AsyncPrintUsers pu;

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
    ArrayList<Users> Users = usersMapper.selectAllByUserName();
    model.addAttribute("Users", Users);
    model.addAttribute("loginUser", loginUser);
    return "wait.html";
  }

  @GetMapping("/wait/step1")
  public SseEmitter pushCount() {


    // finalは初期化したあとに再代入が行われない変数につける（意図しない再代入を防ぐ）
    final SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);//
    // 引数にLongの最大値をTimeoutとして指定する

    try {
      this.pu.count(emitter);
    } catch (IOException e) {
      emitter.complete();
    }
    return emitter;
  }
  @GetMapping("/wait/step2")
  public String ExitWait(Principal prin, ModelMap model){
    String loginUser = prin.getName();
    model.addAttribute("loginUser", loginUser);
    deleteByName();
    return "home.html";
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
