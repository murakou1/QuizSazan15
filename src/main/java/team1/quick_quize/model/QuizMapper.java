package team1.quick_quize.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface QuizMapper {

  @Select("SELECT * from quiz where no = #{no}")
  Quiz selectAllByNo(int no);

  @Select("SELECT answer from quiz where no = #{no}")
  int selectAnswerByNo(int no);

  @Select("SELECT * FROM quiz ORDER BY RAND() LIMIT 1")
  Quiz selectRandomQuiz();
}
