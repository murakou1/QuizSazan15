package team1.quick_quize.model;

public class Users {
  int id;
  String userName;
  int point;

  // Thymeleafでフィールドを扱うためにはgetter/setterが必ず必要
  // vscodeのソースコード右クリック->ソースアクションでsetter/getterを簡単に追加できる
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getPoint() {
    return point;
  }

  public int addPoint(int point){
    point++;
    return point;
  }

  public void setPoint(int point) {
    this.point = point;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
