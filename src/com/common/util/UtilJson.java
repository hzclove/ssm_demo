package com.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/28.
 */
public class UtilJson {

  public static Gson gson = new Gson();
  private static final Type typeMap =  new TypeToken<Map>(){}.getType();

  public static String toJson(Object obj) {
    return gson.toJson(obj);
  }

  public static Object toObject(String json, Class clazz) {
    return gson.fromJson(json, clazz);
  }

  /**
   * 将JSON字符串转换为 HashMap
   * @param json
   * @return
   */
  public static Map getJsonToMap(String json){
    return gson.fromJson(json, typeMap);
  }

  /**
   * 根据key读取字符串值
   * @param jsonObject
   * @param key
   * @return
   */
  public static String getValue(JsonObject jsonObject, String key) {
    JsonElement jsonElement = jsonObject.get(key);
    if (jsonElement != null) {
      return jsonElement.getAsString();
    }

    return "";
  }

  /**
   * 将JavaBean字符串转换为 JSON
   * @param JavaBean
   * @return
   */
  public static  String getJavaBeanToJson(Object javaBean){
    return  gson.toJson(javaBean);
  }

  /**
   * 将ArrayList字符串转换为 JSON
   * @param list
   * @return
   */
  public static String getListToJson(List list){
    return gson.toJson(list);
  }

  /**
   * 将JSON字符串转换为 ArrayListBean
   * @param json
   * @param type
   * @return
   */
  public static  <T> T getJsonToListBean(String json,Type type){
    return gson.fromJson(json, type);
  }

}