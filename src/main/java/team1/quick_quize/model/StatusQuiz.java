package team1.quick_quize.model;

public class StatusQuiz {
  int no;
  String content;
  String choice1;
  String choice2;
  String choice3;
  String choice4;
  int answer;
  int cnt;

  // Thymeleafでフィールドを扱うためにはgetter/setterが必ず必要
  // vscodeのソースコード右クリック->ソースアクションでsetter/getterを簡単に追加できる
  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getChoice1() {
    return choice1;
  }

  public void setChoice1(String choice1) {
    this.choice1 = choice1;
  }

  public String getChoice2() {
    return choice2;
  }

  public void setChoice2(String choice2) {
    this.choice2 = choice2;
  }

  public String getChoice3() {
    return choice3;
  }

  public void setChoice3(String choice3) {
    this.choice3 = choice3;
  }

  public String getChoice4() {
    return choice4;
  }

  public void setChoice4(String choice4) {
    this.choice4 = choice4;
  }

  public int getAnswer() {
    return answer;
  }

  public void setAnswer(int answer) {
    this.answer = answer;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

}
