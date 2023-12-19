package team1.quick_quize.model;

public class Status {
  int num;
  int no;

  // Thymeleafでフィールドを扱うためにはgetter/setterが必ず必要
  // vscodeのソースコード右クリック->ソースアクションでsetter/getterを簡単に追加できる
  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

}
