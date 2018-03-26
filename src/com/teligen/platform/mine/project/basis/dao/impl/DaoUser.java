package com.teligen.platform.mine.project.basis.dao.impl;


import com.teligen.platform.mine.project.basis.vo.VoUser;
import com.teligen.platform.mine.project.basis.dao.base.BDao;
import com.teligen.platform.mine.project.basis.dao.base.IDaoUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("com.teligen.platform.mine.project.basis.dao.impl.DaoUser")
public class DaoUser extends BDao implements IDaoUser {
  private static final Logger log = Logger.getLogger(DaoUser.class);

  /**
   * 添加"用户"。
   * @param voUser 用户对象。
   * @return true：增加成功；false：增加失败。
   */
  @Override
  public boolean insert(VoUser voUser) {
    String statement = "com.teligen.platform.mine.conf.mapper.basis.MapperUser.insert";
    SqlSession session = getSession();
    if (session == null) {
      log.warn("session is null!");
      return false;
    }
    session.insert(statement, voUser);
    session.close();
    return true;
  }

  /**
   * 查询"用户"。
   * @param userId 用户编号。
   * @return 用户对象。
   */
  @Override
  public VoUser select(int userId) {
    String statement = "com.teligen.platform.mine.project.basis.dao.mapper.MapperUser.select";
    SqlSession session = getSession();
    if (session == null) {
      log.warn("session is null!");
      return null;
    }
    VoUser voUser = session.selectOne(statement, userId);
    session.close();
    return voUser;

  }

  /**
   * 查询"用户"。
   * @return 用户对象列表。
   */
  @Override
  public List<VoUser> query() {
    String statement = "com.teligen.platform.mine.project.basis.dao.mapper.MapperUser.query";
    SqlSession session = getSession();
    if (session == null) {
      log.warn("session is null!");
      return null;
    }
    List<VoUser> list = session.selectList(statement);
    session.close();
    return list;
  }

  /**
   * 更改"用户"。
   * @param voUser 用户对象。
   */
  @Override
  public void update(VoUser voUser) {
    String statement = "com.teligen.platform.mine.project.basis.dao.mapper.MapperUser.update";
    SqlSession session = getSession();
    if (session == null) {
      log.warn("session is null!");
      return;
    }
    session.update(statement, voUser);
    session.close();
  }

  /**
   * 删除"用户"。
   * @param userId 用户编号。
   */
  @Override
  public void delete(int userId) {
    String statement = "com.teligen.platform.mine.project.basis.dao.mapper.MapperUser.delete";
    SqlSession session = getSession();
    if (session == null) {
      log.warn("session is null!");
      return;
    }
    session.delete(statement, userId);
    session.close();
  }

}
