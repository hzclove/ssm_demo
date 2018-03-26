package com.teligen.platform.mine.project.basis.service.impl;

import com.teligen.platform.mine.project.basis.vo.VoUser;
import com.teligen.platform.mine.project.basis.dao.base.IDaoUser;
import com.teligen.platform.mine.project.basis.service.base.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service("com.teligen.platform.mine.project.basis.service.impl.ServiceUser")
public class ServiceUser implements IServiceUser {

  @Resource(name="com.teligen.platform.mine.project.basis.dao.impl.DaoUser")
  private IDaoUser daoUser;

  /**
   * 添加"用户"。
   * @param voUser 用户对象。
   * @return true：增加成功；false：增加失败。
   */
  @Override
  public boolean insert(VoUser voUser) {
    return daoUser.insert(voUser);
  }

  /**
   * 查询"用户"。
   * @param userId 用户编号。
   * @return 用户对象。
   */
  @Override
  public VoUser select(int userId) {
    return daoUser.select(userId);
  }

  /**
   * 查询"用户"。
   * @return 用户对象列表。
   */
  @Override
  public List<VoUser> query() {
    return daoUser.query();
  }

  /**
   * 更改"用户"。
   * @param voUser 用户对象。
   */
  @Override
  public void update(VoUser voUser) {
    daoUser.update(voUser);
  }

  /**
   * 删除"用户"。
   * @param userId 用户编号。
   */
  @Override
  public void delete(int userId) {
    daoUser.delete(userId);
  }

}
