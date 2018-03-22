package com.teligen.platform.mine.project.basis.dao.base;

import com.teligen.platform.mine.project.basis.vo.VoUser;

import java.util.List;

public interface IDaoUser {

  /**
   * 添加"用户"。
   * @param voUser 用户对象。
   * @return true：增加成功；false：增加失败。
   */
  public boolean insert(VoUser voUser);

  /**
   * 查询"用户"。
   * @param userId 用户编号。
   * @return 用户对象。
   */
  public VoUser select(int userId);

  /**
   * 查询"用户"。
   * @return 用户对象列表。
   */
  public List<VoUser> query();

  /**
   * 更改"用户"。
   * @param voUser 用户对象。
   */
  public void update(VoUser voUser);

  /**
   * 删除"用户"。
   * @param userId 用户编号。
   */
  public void delete(int userId);
}
