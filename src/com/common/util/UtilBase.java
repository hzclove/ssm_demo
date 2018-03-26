package com.common.util;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/3/23.
 */
public class UtilBase {

  public static final String DefaultTime = "1970-1-1 08:00:00"; //Ĭ��ʱ���ַ���
  private static Gson gson = new Gson();
  private static JsonParser jsonParser = new JsonParser();

  public static final class DateFormat {
    public static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat hhMMss = new SimpleDateFormat("HH:mm:ss");
  }

  //<<-------------------------- ���� --------------------------

  /**
   * @param dateStr String �����ַ���
   * @return ���ݴ�����ַ���������Ӧ�����ڶ���
   */
  public static Date toDate(String dateStr) {
    if (isEmpty(dateStr)) {
      dateStr = DefaultTime;
    }
    return toDate(dateStr, DateFormat.yyyyMMddHHmmss);
  }

  /**
   * @param dateStr String �����ַ���
   * @param formatString String ���ڵĸ�ʽ�����磺yyyy-MM-dd HH:mm:ss
   * @return ���ݴ�����ַ���������Ӧ�����ڶ���
   */
  public static Date toDate(String dateStr, String formatString) {
    if (isEmpty(dateStr)) {
      dateStr = DefaultTime;
    }
    SimpleDateFormat format = null;
    if (isEmpty(formatString)) {
      format = DateFormat.yyyyMMddHHmmss;
    } else {
      format = new SimpleDateFormat(formatString);
    }
    return toDate(dateStr, format);
  }

  /**
   * �����ݿ�������������ڸ�ʽ(ʱ���)ת��Ϊ���������ڸ�ʽ������ʾ
   * @param dateStr ���ݿ�������������ڸ�ʽ
   * @return ���������ڸ�ʽ
   */
  public static String format(String dateStr) {
    if (isEmpty(dateStr)) {
      return DefaultTime;
    }
    return format(dateStr, DateFormat.yyyyMMddHHmmss);
  }

  /**
   * �����ݿ�������������ڸ�ʽ(ʱ���)ת��Ϊ���������ڸ�ʽ������ʾ
   * @param dateStr ���ݿ�������������ڸ�ʽ
   * @return ���������ڸ�ʽ
   */
  public static String format(String dateStr, String formatString) {
    if (isEmpty(dateStr)) {
      return DefaultTime;
    }
    SimpleDateFormat sdf = null;
    if (isEmpty(formatString)) {
      sdf = DateFormat.yyyyMMddHHmmss;
    } else {
      sdf = new SimpleDateFormat(formatString);
    }
    return format(dateStr, sdf);
  }

  private static String format(String dateStr, SimpleDateFormat format) {
    try {
      Date eventDate = new Date();
      long timeLong = Long.valueOf(dateStr).longValue();
      if (dateStr.length() < 13) {
        timeLong = Long.valueOf(dateStr).longValue() * 1000;
      }
      eventDate.setTime(timeLong);
      return format.format(eventDate);
    } catch (Exception e) {
      return DefaultTime;
    }
  }

  private static Date toDate(String dateStr, SimpleDateFormat format) {
    Date date = null;
    try {
      date = format.parse(dateStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return date;
  }

  public static Date getDate(String dateStr, String formatString) {
    Date date = null;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(formatString);
      date = sdf.parse(dateStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return date;
  }

  public static Date getDate(String dateStr) {
    return getDate(dateStr, "yyyy-MM-dd HH:mm:ss");
  }

  public static String getDate() {
    Date date = new Date();
    String dateStr = DateFormat.yyyyMMddHHmmss.format(date);
    return dateStr;
  }

  public static long getSecond(String dateStr) {
    long time = 0;
    time = getDate(dateStr).getTime() / 1000;
    return time;
  }

  public static long getSecond() {
    long time = 0;
    time = new Date().getTime() / 1000;
    return time;
  }

  public static long getTime(String dateStr) {
    long time = -1;
    String format = "yyyy-MM-dd HH:mm:ss";
    Date date = getDate(dateStr, format);
    if (date != null) {
      time = getDate(dateStr, format).getTime();
    }
    return time;
  }

  public static long getTimeNoHMS(String dateStr) {
    long time = -1;
    String format = "yyyy-MM-dd";
    Date date = getDate(dateStr, format);
    if (date != null) {
      time = getDate(dateStr, format).getTime();
    }
    return time;
  }

  public static String toDate(long value) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return format.format(new Date(value * 1000L));
  }

  public static Map<String, String> toMap(Object object) {
    Map<String, String> map = new HashMap<>();
    if (object != null) {
      JSONObject jsonObject = JSONObject.fromObject(object);
      Iterator iterator = jsonObject.keys();
      while (iterator.hasNext()) {
        String key = (String) iterator.next();
        String value = jsonObject.get(key).toString();
        map.put(key, UtilBase.toString(value));
      }
    }
    return map;
  }

  public static Map<String, Object> toMap2(Object object) {
    Map<String, Object> map = new HashMap<>();
    if (object != null) {
      JSONObject jsonObject = JSONObject.fromObject(object);
      Iterator iterator = jsonObject.keys();
      while (iterator.hasNext()) {
        String key = (String) iterator.next();
        Object value = jsonObject.get(key);
        map.put(key, value);
      }
    }
    return map;
  }

  public static Object toBean(Map<String, String> map, Class clazz) {
    if (map == null) {
      return null;
    }
    JSONObject jSONObject = JSONObject.fromObject(map);
    String param = jSONObject.toString();
    JsonObject jsonObject = jsonParser.parse(param).getAsJsonObject();
    return gson.fromJson(jsonObject, clazz);
  }

  public static String getKey(String extendType, String extendCode) {
    if (StringUtils.isBlank(extendType) || StringUtils.isBlank(extendCode)) {
      return null;
    }
    return extendType + "_" + extendCode;
  }

  //>>-------------------------- ���� --------------------------

  //<<-------------------------- �ַ��� --------------------------

  /**
   * �ж��ַ����Ƿ�Ϊ��(null��"")
   */
  public static boolean isEmpty(String value) {
    return (value == null || "".equals(value.trim()));
  }

  /**
   * �ж������Ƿ�Ϊ��(0Ϊ��)
   */
  public static boolean isEmpty(int value) {
    return (value == 0);
  }

  /**
   * �ж��ַ��������Ƿ�Ϊ��(null�򳤶�Ϊ0)
   * @param array
   * @return
   */
  public static boolean isEmpty(String[] array) {
    return array == null || array.length <= 0;
  }

  public static boolean isEmpty(Map map) {
    return map == null || map.isEmpty();
  }

  /**
   * �ж�ָ���ַ����Ƿ������֡����ﲻ�ж��Ƿ�Ϸ������֣���"01"����true
   * @param value
   * @return
   */
  public static boolean isNumber(String value) {
    if (isEmpty(value)) {
      return false;
    }
    for (int i = 0; i < value.length(); i++) {
      if ((value.charAt(i) > '9') || (value.charAt(i) < '0')) {
        return false;
      }
    }
    return true;
  }

  /**
   * �Ƚ������ַ����Ƿ���ȣ����Դ�Сд�������һ��Ϊ null������ false��
   * @param one
   * @param two
   * @param ignoreCase �Ƿ���Դ�Сд��true �ǡ�
   * @return
   */
  public static boolean isEqual(String one, String two, boolean ignoreCase) {
    if (one == null || two == null) {
      return false;
    }
    if (ignoreCase) {
      one = one.toLowerCase();
      two = two.toLowerCase();
    }
    return one.equals(two);
  }

  /**
   * �Ƚ������ַ����Ƿ���ȣ����Դ�Сд�������һ��Ϊ null������ false��
   * @param one
   * @param two
   * @return
   */
  public static boolean isEqual(String one, String two) {
    return isEqual(one, two, true);
  }

  /**
   * ת��Ϊ String��ȥ�����߿ո�null ת��Ϊ ""��
   * @param value
   * @return
   */
  public static String toString(Object value) {
    return value == null ? "" : String.valueOf(value).trim();
  }

  /**
   * ת��Ϊ String��ȥ�����߿ո�null ת��Ϊ ""��
   * @param value
   * @return
   */
  public static String toString(String value) {
    return value == null ? "" : value.trim();
  }

  /**
   * ת��Ϊ String��
   * @param value
   * @return
   */
  public static String toString(int value) {
    return String.valueOf(value);
  }

  public static String toString(Element element) {
    return element == null ? "" : element.getTextTrim();
  }

  /**
   * ת��ΪInteger
   * @param value
   * @return
   */
  public static Integer toInteger(Object value) {
    String valueStr = toString(value);
    return toInteger(valueStr);
  }

  public static Double toDouble(Object value) {
    String valueStr = toString(value);
    return toDouble(valueStr);
  }

  public static Double toDouble(String value) {
    return isEmpty(value) ? 0.0 : Double.parseDouble(value);
  }

  public static Float toFloat(Object value) {
    String valueStr = toString(value);
    return toFloat(valueStr);
  }

  public static Float toFloat(String value) {
    return isEmpty(value) ? 0 : Float.parseFloat(value);
  }

  /**
   * ת��Ϊint
   * @param value
   * @return
   */
  public static int toInt(Object value) {
    String valueStr = toString(value);
    return toInteger(valueStr);
  }

  /**
   * ת��ΪInteger
   * @param value
   * @return
   */
  public static Integer toInteger(String value) {
    return isEmpty(value) ? 0 : Integer.parseInt(value);
  }

  /**
   * ת��ΪBoolean
   * @param value
   * @return
   */
  public static Boolean toBoolean(Object value) {
    String valueStr = toString(value);
    return toBoolean(valueStr);
  }

  /**
   * ת��ΪBoolean
   * @param value
   * @return
   */
  public static Boolean toBoolean(String value) {
    return isEmpty(value) ? false : Boolean.parseBoolean(value);
  }

  public static String toLowerFirst(String value) {
    return value;
  }
  //>>-------------------------- �ַ��� --------------------------

  //<<-------------------------- ���� --------------------------

  //>>-------------------------- ���� --------------------------

  //<<-------------------------- list --------------------------

  public static boolean isEmpty(List list) {
    return list == null || list.size() <= 0;
  }

  public static boolean isNotEmpty(List list) {
    return list != null && list.size() > 0;
  }

  //>>-------------------------- list --------------------------

  public static byte[] toBase64(String text) {
    return Base64.decodeBase64(text.getBytes());
  }

  /**
   * �Ƿ��ƺ�
   * @param str
   * @return
   */
  public static boolean isCarNo(String str) {
    String pattern = "^([�����弽³ԥ�ƺڼ���������������Ӷ���ʽ�����������ش�����ʹ��A-Za-z]{1}[A-Za-z]{1}[�������弽³ԥ�ƺڼ���������������Ӷ���ʽ�����������ش�����]{0,1}[A-Za-z0-9]{4}[A-Za-z0-9��ѧ���۰�]{1})$";
    return str.matches(pattern);
  }

  public static boolean isImei(String str) {
    String pattern = "\\d{1,15}";
    return str.matches(pattern);
  }

  /**
   * �Ƿ��ǹ̶��绰
   * @param str
   * @return
   */
  public static boolean isTelephone(String str) {
    String pattern = "(^\\d{8}$)";
    return str.matches(pattern);
  }

  /**
   * �Ƿ����ֻ�����
   * @param str
   * @return
   */
  public static boolean isPhonenum(String str) {
    String pattern = "^[1][3-8]+\\d{9}";
    return str.matches(pattern);
  }

  /**
   * �Ƿ������֤����
   * @param str
   * @return
   */
  public static boolean isSfz(String str) {
    String pattern = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    if (str.length() == 18) {
      pattern = "^\\d{17}([0-9]|X)";
    }
    return str.matches(pattern);
  }

  /**
   * �Ƿ���ip
   * @param str
   * @return
   */
  public static boolean isIp(String str) {
    String pattern = "^(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])";
    return str.matches(pattern);
  }

  public static String phonenumRemove86(String phonenum) {
    if (UtilBase.isEmpty(phonenum)) {
      return null;
    }
    if (phonenum.length() == 13 && phonenum.startsWith("86")) {
      phonenum = phonenum.substring(2, phonenum.length());
    }
    if (phonenum.length() == 14 && phonenum.startsWith("086")) {
      phonenum = phonenum.substring(3, phonenum.length());
    }
    return phonenum;
  }

  public static void main(String[] args) {
    //    System.out.print(format("1453340538"));
    System.out.print(isTelephone("63367368"));
  }
}
