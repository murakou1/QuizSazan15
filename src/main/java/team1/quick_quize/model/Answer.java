package team1.quick_quize.model;

public class Answer {
  int no;
  int id;
  String userName;
  int choice;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getChoice() {
    return choice;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setChoice(int choice) {
    this.choice = choice;
  }

}
