package util;

import org.apache.commons.codec.digest.DigestUtils;

public class AuthUtil {

  public static String md5Encode(String text) {
    return DigestUtils.md5Hex(text);
  }
}
