package com.teligen.platform.mine.project.basis.dao.base;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BDao {

  @Autowired
  private SqlSessionFactory sqlSessionFactoryPlatform;

  //<<----------------------------- initialize -----------------------------

  //>>----------------------------- initialize -----------------------------

  //<<----------------------------- public -----------------------------

  //>>----------------------------- public -----------------------------

  //<<----------------------------- protected -----------------------------

  protected SqlSession getSession() {
    return sqlSessionFactoryPlatform.openSession();
  }

  //>>----------------------------- protected -----------------------------

  //<<----------------------------- private -----------------------------

  //<<<----------------------------- common -----------------------------

  //>>>----------------------------- common -----------------------------

  //>>----------------------------- private -----------------------------

  //<<----------------------------- get -----------------------------

  //>>----------------------------- get -----------------------------

  //<<----------------------------- set -----------------------------

  //>>----------------------------- set -----------------------------

  //<<----------------------------- get set -----------------------------

  //>>----------------------------- get set -----------------------------
}
