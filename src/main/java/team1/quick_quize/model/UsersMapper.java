package team1.quick_quize.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface UsersMapper {

  @Select("SELECT id,userName,point from Users where id = #{id}")
  Users selectById(int id);

  @Select("SELECT point from Users where id = #{id}")
  int selectPointById(int id);

  @Select("SELECT id from Users where userName=#{name}")
  int selectByName(String name);

  /**
   * #{userName}などはinsertの引数にあるChamberクラスのフィールドを表しています 引数に直接String
   * userNameなどと書いてもいけるはず
   * 下記のOptionsを指定すると，insert実行時にAuto incrementされたIDの情報を取得できるようになる useGeneratedKeys
   * = true -> Keyは自動生成されることを表す keyColumn : keyになるテーブルのカラム名 keyProperty :
   * keyになるJavaクラスのフィールド名
   *
   * @param Users
   */
  @Insert("INSERT INTO Users (userName,point) VALUES (#{userName},#{point});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(Users Users);

  @Select("SELECT * from users;")
  ArrayList<Users> selectAllByUserName();

  @Delete("delete from users where userName=#{userName};")
  void deleteByName(String userName);

  @Update("update users set point = #{point} where id=#{id};")
  void updateById(int point, int id);
}
