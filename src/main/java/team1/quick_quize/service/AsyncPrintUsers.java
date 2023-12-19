package team1.quick_quize.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import team1.quick_quize.model.Users;
import team1.quick_quize.model.UsersMapper;

@Service
public class AsyncPrintUsers {
  private final Logger logger = LoggerFactory.getLogger(AsyncPrintUsers.class);

  @Autowired
  UsersMapper usersMapper;

  boolean dbUpdated = false;

  int pushButton = 0;

  @Async
  public void pushName(SseEmitter emitter) {
    dbUpdated = true;
    try {
      while (true) {// 無限ループ
        if (pushButton == 1) {
          emitter.send(1);
        }
        // DBが更新されていなければ0.5s休み
        if (false == dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        // DBが更新されていれば更新後のフルーツリストを取得してsendし，1s休み，dbUpdatedをfalseにする
        ArrayList<Users> Users = usersMapper.selectAllByUserName();
        emitter.send(Users);
        TimeUnit.MILLISECONDS.sleep(1000);
        dbUpdated = false;
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowFruitsList complete");
  }

  public void setButton(int flag) {
    this.pushButton = flag;
  }

}
