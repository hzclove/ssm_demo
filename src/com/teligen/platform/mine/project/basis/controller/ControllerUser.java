package com.teligen.platform.mine.project.basis.controller;

import com.common.util.MsgResponse;
import com.common.util.UtilBase;
import com.google.gson.Gson;
import com.teligen.platform.mine.project.base.BController;
import com.teligen.platform.mine.project.basis.vo.VoUser;
import com.teligen.platform.mine.project.basis.service.base.IServiceUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mine/basis/user.do")
public class ControllerUser extends BController {
  private static final Logger log = Logger.getLogger(ControllerUser.class);
  private static Gson gson = new Gson();

  @Resource(name="com.teligen.platform.mine.project.basis.service.impl.ServiceUser")
  private IServiceUser serviceUser;

  //<<-------------------------- public method start  --------------------------

  //<<<-------------------------- insert method start  --------------------------

  /**
   * 插入一条"用户"记录。
   * @param param 传入参数，Json格式。
   * @param request
   * @param response
   */
  @RequestMapping(params = "method=insert")
  public void insert(String param, HttpServletRequest request, HttpServletResponse response) {
    if (serviceUser == null || UtilBase.isEmpty(param)) {
      log.warn("serviceUser is null or param is empty!");
      return;
    }
    try {
      VoUser voUser = toBean(param);
      boolean ok = insertUser(voUser);
      output(response, ok, "insert user.");
    } catch (Exception e) {
      log.error("insert user error!", e);
      output(response, false, "insert user.");
    }
  }

  //>>>-------------------------- insert method end  --------------------------

  //<<<-------------------------- select method start  --------------------------

  /**
   * 按条件查询"用户"结果。
   * @param param 传入参数，Json格式。
   * @param request
   * @param response
   */
  @RequestMapping(params = "method=select")
  public void select(String param, HttpServletRequest request, HttpServletResponse response) {
    if (serviceUser == null || UtilBase.isEmpty(param)) {
      log.warn("serviceUser is null or param is empty!");
      return;
    }
    try {
      VoUser voUser = toBean(param);
      VoUser result = selectUser(voUser);
      output(response, result);
    } catch (Exception e) {
      log.error("select user error!", e);
      output(response, false, "select user.");
    }
  }

  //>>>-------------------------- select method end  --------------------------

  //<<<-------------------------- query method start  --------------------------

  /**
   * 查询所有"用户"的记录。
   * @param request
   * @param response
   */
  @RequestMapping(params = "method=query")
  public void query(HttpServletRequest request, HttpServletResponse response) {
    try {
      List<VoUser> list = queryUser();
      output(response, list);
    } catch (Exception e) {
      log.error("query error!", e);
      output(response, false, "query user.");
    }
  }

  //>>>-------------------------- query method end  --------------------------

  //<<<-------------------------- update method start  --------------------------

  /**
   * 更新"用户"信息。
   * @param param 传入参数，Json格式。
   * @param request
   * @param response
   */
  @RequestMapping(params = "method=update")
  public void update(String param, HttpServletRequest request, HttpServletResponse response) {
    if (serviceUser == null || UtilBase.isEmpty(param)) {
      log.warn("serviceUser is null or param is empty!");
      return;
    }
    try {
      VoUser voUser = toBean(param);
      boolean ok = updateUser(voUser);
      output(response, ok, "update user.");
    } catch (Exception e) {
      log.error("update error!", e);
      output(response, false, "update user.");
    }
  }

  //>>>-------------------------- update method end  --------------------------

  //<<<-------------------------- delete method start  --------------------------

  /**
   * 删除"用户"信息。
   * @param param 传入参数，Json格式。
   * @param request
   * @param response
   */
  @RequestMapping(params = "method=delete")
  public void delete(String param, HttpServletRequest request, HttpServletResponse response) {
    if (serviceUser == null || UtilBase.isEmpty(param)) {
      log.warn("serviceUser is null or param is empty!");
      return;
    }
    try {
      VoUser voUser = toBean(param);
      boolean ok = deleteUser(voUser);
      output(response, ok, "delete user.");
    } catch (Exception e) {
      log.error("delete error!", e);
      output(response, false, "delete user.");
    }
  }

  //>>>-------------------------- delete method end  --------------------------

  //>>-------------------------- public method end --------------------------

  //<<-------------------------- private method start --------------------------

  //<<<-------------------------- common method start --------------------------
  private void output(HttpServletResponse response, boolean success, String msg) {
    MsgResponse msgResponse = createMsgResponse(success, msg);
    writeResponse(response, msgResponse);
  }

  private void output(HttpServletResponse response, VoUser voUser) {
    MsgResponse msgResponse = createMsgResponse(true);
    fillMsgResponse(msgResponse, voUser);
    writeResponse(response, msgResponse);
  }

  private void output(HttpServletResponse response, List<VoUser> list) {
    MsgResponse msgResponse = createMsgResponse(true);
    fillMsgResponse(msgResponse, list);
    writeResponse(response, msgResponse);
  }

  private void fillMsgResponse(MsgResponse msgResponse, VoUser voUser) {
    Map<String, String> map = toMap(voUser);
    msgResponse.setPair(map);
  }

  private void fillMsgResponse(MsgResponse msgResponse, List<VoUser> list) {
    List<Map<String, String>> pairs = new ArrayList<>();
    for (VoUser voUser : list) {
      Map<String, String> map = toMap(voUser);
      pairs.add(map);
    }
    msgResponse.setBucket(pairs);
  }

  private Map<String, String> toMap(VoUser voUser) {
    Map<String, String> map = new HashMap<>();
    if (voUser != null) {
      map.put("userId", UtilBase.toString(voUser.getUserId()));
      map.put("userName", UtilBase.toString(voUser.getUserName()));
      map.put("password", UtilBase.toString(voUser.getPassword()));
      map.put("memo", UtilBase.toString(voUser.getMemo()));
    }
    return map;
  }

  private VoUser toBean(String param) {
    return gson.fromJson(param, VoUser.class);
  }

  //>>>-------------------------- common method end --------------------------

  //<<<-------------------------- inner method start --------------------------
  private boolean insertUser(VoUser voUser) throws Exception {
    if (voUser == null) {
      log.warn("beanUser is null!");
      return false;
    }
    return serviceUser.insert(voUser);
  }

  private VoUser selectUser(VoUser voUser) throws Exception {
    if (voUser == null) {
      log.warn("beanUser is null!");
      return null;
    }
    int userId = voUser.getUserId();
    return serviceUser.select(userId);
  }

  private List<VoUser> queryUser() {
    try {
      return serviceUser.query();
    } catch (Exception e) {
      log.error("queryUser error!", e);
      return null;
    }
  }

  private boolean updateUser(VoUser voUser) {
    try {
      serviceUser.update(voUser);
      return true;
    } catch (Exception e) {
      log.error("updateUser error!", e);
      return false;
    }
  }

  private boolean deleteUser(VoUser voUser) {
    try {
      int userId = voUser.getUserId();
      serviceUser.delete(userId);
      return true;
    } catch (Exception e) {
      log.error("deleteUser error!", e);
      return false;
    }
  }

  //>>>-------------------------- inner method end --------------------------

  //>>-------------------------- private method end --------------------------

}
