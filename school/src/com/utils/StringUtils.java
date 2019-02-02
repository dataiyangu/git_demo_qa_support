package com.utils;

public class StringUtils
{
  public static String trim(String str)
  {
    return str == null ? "" : str.trim();
  }

  public static String trim(Object o) {
    return trim((String)o);
  }

  public static boolean isEmpty(String str) {
    str = trim(str);
    if (str.equals("")) {
      return true;
    }
    return false;
  }

  public static boolean isNotEmpty(String str)
  {
    return !isEmpty(str);
  }

  public static boolean isEmpty(Object object) {
    return isEmpty((String)object);
  }

  public static boolean isNotEmpty(Object object) {
    return !isEmpty(object);
  }
}
