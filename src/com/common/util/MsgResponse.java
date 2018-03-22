package com.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 * 返回不同的类型，不过可以用spring自带注解@ResponseBody默认返回json字符串 
 */
public class MsgResponse {
  private int code;
  private String msg;
  private int type;
  private Map<String, String> pair = null;
  private List<Map<String, String>> bucket = null;
  private List<Map<String, String>> column = null;
  private List<List<String>> record = null;
  private List<String> arr = null;
  private List<Map<String, Object>> bucketObj = null;

  public MsgResponse() {
    this.pair = new HashMap<>();
    this.bucket = new ArrayList<>();
    this.column = new ArrayList<>();
    this.record = new ArrayList<>();
    this.arr = new ArrayList<>();
    this.bucketObj = new ArrayList<>();
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Map<String, String> getPair() {
    return pair;
  }

  public void setPair(Map<String, String> pair) {
    this.pair = pair;
  }

  public List<Map<String, String>> getBucket() {
    return bucket;
  }

  public void setBucket(List<Map<String, String>> bucket) {
    this.bucket = bucket;
  }

  public List<Map<String, String>> getColumn() {
    return column;
  }

  public void setColumn(List<Map<String, String>> column) {
    this.column = column;
  }

  public List<List<String>> getRecord() {
    return record;
  }

  public void setRecord(List<List<String>> record) {
    this.record = record;
  }

  public List<String> getArr() {
    return arr;
  }

  public void setArr(List<String> arr) {
    this.arr = arr;
  }

  public List<Map<String, Object>> getBucketObj() {
    return bucketObj;
  }

  public void setBucketObj(List<Map<String, Object>> bucketObj) {
    this.bucketObj = bucketObj;
  }
}
