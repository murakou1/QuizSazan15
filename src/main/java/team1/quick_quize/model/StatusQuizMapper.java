package team1.quick_quize.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface StatusQuizMapper {

  @Select("select count(*) from statusquiz")
  int selectCount();

  @Select("select * from statusquiz")
  StatusQuiz selectStatus();

  @Select("select cnt from statusquiz")
  int selectCnt();

  @Insert("INSERT INTO statusquiz (no,content,choice1,choice2,choice3,choice4,answer) VALUES (#{no},#{content},#{choice1},#{choice2},#{choice3},#{choice4},#{answer})")
  void insertStatus(Quiz Status);

  @Update("Update statusquiz set cnt = #{quizecnt}")
  void updateCnt(int quizecnt);

  @Delete("delete from statusquiz")
  void deleteStatus();

}
