package com.teligen.platform.mine.project.basis.dao.base;

import com.teligen.platform.mine.project.basis.vo.VoUser;

import java.util.List;

public interface IDaoUser {

  /**
   * ���"�û�"��
   * @param voUser �û�����
   * @return true�����ӳɹ���false������ʧ�ܡ�
   */
  public boolean insert(VoUser voUser);

  /**
   * ��ѯ"�û�"��
   * @param userId �û���š�
   * @return �û�����
   */
  public VoUser select(int userId);

  /**
   * ��ѯ"�û�"��
   * @return �û������б�
   */
  public List<VoUser> query();

  /**
   * ����"�û�"��
   * @param voUser �û�����
   */
  public void update(VoUser voUser);

  /**
   * ɾ��"�û�"��
   * @param userId �û���š�
   */
  public void delete(int userId);
}
