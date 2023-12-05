package team1.quick_quize.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface AnswerMapper {

  @Select("SELECT no,id,userName,choise from answer where id = #{id}")
  Answer selectById(int id);

  /**
   * #{userName}などはinsertの引数にあるChamberクラスのフィールドを表しています 引数に直接String
   * userNameなどと書いてもいけるはず
   * 下記のOptionsを指定すると，insert実行時にAuto incrementされたIDの情報を取得できるようになる useGeneratedKeys
   * = true -> Keyは自動生成されることを表す keyColumn : keyになるテーブルのカラム名 keyProperty :
   * keyになるJavaクラスのフィールド名
   *
   * @param Users
   */
  @Insert("INSERT INTO answer (id,userName,choice) VALUES (#{id},#{userName},#{choice});")
  @Options(useGeneratedKeys = true, keyColumn = "no", keyProperty = "no")
  void insertAnswer(Answer Answer);

  @Select("SELECT * from answer;")
  ArrayList<Answer> selectAllByAnswer();

  @Delete("delete from answer no=#{no};")
  void deleteByNo(int no);
}
