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
import team1.quick_quize.model.Answer;
import team1.quick_quize.model.AnswerMapper;
import team1.quick_quize.model.Quiz;
import team1.quick_quize.model.QuizMapper;
import team1.quick_quize.service.AsyncPrintUsers;

@Controller
public class QuuizeController {
  public static int cnt=0;
  public static int quize_no=0;

  @Autowired
  UsersMapper usersMapper;

  @Autowired
  QuizMapper quizMapper;

  @Autowired
  AnswerMapper answerMapper;

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
    ArrayList<Users> users = usersMapper.selectAllByUserName();
    model.addAttribute("users", users);
    model.addAttribute("loginUser", loginUser);
    return "wait.html";
  }

  @GetMapping("/wait/step1")
  public SseEmitter pushName() {

    final SseEmitter sseEmitter = new SseEmitter();
    this.pu.pushName(sseEmitter);
    return sseEmitter;
  }

  @GetMapping("/wait/step2")
  public String ExitWait(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    model.addAttribute("loginUser", loginUser);
    usersMapper.deleteByName(loginUser);
    return "home.html";
  }

  @GetMapping("/quize")
  public String quize(Principal prin, ModelMap model) {
    Quiz quiz = quizMapper.selectAllByNo(1);
    model.addAttribute("quiz", quiz);
    ArrayList<Users> Users = usersMapper.selectAllByUserName();
    model.addAttribute("Users", Users);

    return "quize.html";
  }

  @GetMapping("/answer/{param}")
  public String answer(@PathVariable String param, Principal prin, ModelMap model) {
    int choice = Integer.parseInt(param);
    String userName = prin.getName();
    int id = usersMapper.selectByName(userName);

    Answer answer = new Answer();
    answer.setChoice(choice);
    answer.setUserName(prin.getName());
    answer.setId(id);
    answerMapper.insertAnswer(answer);

    int answer_num = quizMapper.selectAnswerByNo(++quize_no);
    if (choice == answer_num) {
      int p = usersMapper.selectPointById(1);
      p = p + 3;
      usersMapper.updateById(p, 1);
    }

    Quiz quiz = quizMapper.selectAllByNo(--quize_no);
    model.addAttribute("quiz", quiz);
    ArrayList<Users> Users = usersMapper.selectAllByUserName();
    model.addAttribute("Users", Users);
    return "answer.html";
  }

  @GetMapping("/result")
  public String result(Principal prin, ModelMap model) {
    cnt++;

    if(cnt==3){
      cnt=0;
      quize_no=0;
      ArrayList<Users> Users2 = usersMapper.selectAllByUserName();
      model.addAttribute("Users", Users2);
      return "result.html";
    }

    Quiz quiz = quizMapper.selectAllByNo(++quize_no);
    model.addAttribute("quiz", quiz);
    ArrayList<Users> Users = usersMapper.selectAllByUserName();
    model.addAttribute("Users", Users);
    return "quize.html";
  }

  @GetMapping("/randomQuiz")
  public String randomQuiz(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    model.addAttribute("loginUser", loginUser);

    // 修正: QuizMapperのメソッドを呼び出してランダムなクイズを取得
    Quiz randomQuiz = quizMapper.selectRandomQuiz();
    model.addAttribute("quiz", randomQuiz);

    ArrayList<Users> users = usersMapper.selectAllByUserName();
    model.addAttribute("users", users);

    return "quize.html";
  }
}
