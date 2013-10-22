package com.chy.util;

import java.io.UnsupportedEncodingException;

public class LanguageUtil
{
  private static final int[] HanZiCode = { 45217, 45253, 45761, 46318, 46826, 47010, 47297, 47614, 48119, 49062, 49324, 49896, 50371, 50614, 50622, 50906, 51387, 51446, 52218, 52698, 52980, 53689, 54481, 55456 };

  private static final int LENGTH = HanZiCode.length;

  public static String getPinYin(String word)
  {
    char c = '`';
    try {
      byte[] byte1 = word.getBytes("gb2312");
      if (byte1.length == 2) {
        int codeValue = (byte1[0] + 256) * 256 + byte1[1] + 256;
        if ((codeValue >= HanZiCode[0]) && (codeValue <= HanZiCode[(LENGTH - 1)]))
        {
          for (int i = 0; i < LENGTH; ++i) {
            if (codeValue >= HanZiCode[i]) {
              if (c + '\1' == 105)
                c = (char)(c + '\2');
              else if (c + '\1' == 117)
                c = (char)(c + '\3');
              else {
                c = (char)(c + '\1');
              }
            }
          }
          return c + "";
        }
      }
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    return word;
  }

  public static String chinese2PinYin(String str)
  {
    int length = str.length();
    String result = "";
    for (int i = 0; i < length; ++i) {
      result = result + getPinYin(str.substring(i, i + 1));
    }
    return result;
  }
}