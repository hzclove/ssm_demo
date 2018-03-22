package com.teligen.platform.mine.project.base;

import org.apache.log4j.Logger;

import com.common.util.Const;
import com.common.util.MsgResponse;
import com.common.util.UtilJson;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class BController {
  private static final String contentType_default = "json";

  private static final Logger log = Logger.getLogger(BController.class);

  //<<----------------------------- initialize -----------------------------

  //>>----------------------------- initialize -----------------------------

  //<<----------------------------- public -----------------------------

  //>>----------------------------- public -----------------------------

  //<<----------------------------- protected -----------------------------

  //<<<----------------------------- createMsgResponse -----------------------------
  protected final MsgResponse createMsgResponse(int code, String msg) {
    MsgResponse msgResponse = new MsgResponse();
    msgResponse.setCode(code);
    msgResponse.setMsg(msg);
    return msgResponse;
  }

  protected final MsgResponse createMsgResponse(boolean ok, String msg) {
    int code = Const.code.success;
    if (!ok) {
      code = Const.code.error;
    }
    return createMsgResponse(code, msg);
  }

  protected final MsgResponse createMsgResponse(boolean ok) {
    return createMsgResponse(ok, null);
  }

  //>>>----------------------------- createMsgResponse -----------------------------

  //<<<----------------------------- normal -----------------------------
  protected final void writeResponse(HttpServletResponse response, MsgResponse msgResponse) {
    if (response == null) {
      log.warn("response is null!");
      return;
    }
    if (msgResponse == null) {
      log.warn("msgResponse is null!");
      return;
    }
    String content = UtilJson.toJson(msgResponse);
    writeContent(response, content);
  }

  private void writeContent(HttpServletResponse response, String content) {
    if (response == null) {
      log.warn("response is null!");
      return;
    }
    PrintWriter printWriter = null;
    try {
      response.setContentType(contentType_default);
      printWriter = response.getWriter();
      printWriter.write(content);
      printWriter.flush();
    } catch (Exception e) {
      log.error("write error!", e);
    } finally {
      if (printWriter != null) {
        printWriter.close();
      }
    }
  }

  //>>>----------------------------- normal -----------------------------

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
