package util;

import org.apache.commons.codec.digest.DigestUtils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class AuthUtil {

  public static String md5Encode(String text) {
    try {

      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(text.getBytes());
      byte[] digest = md5.digest();
      String myHash = DatatypeConverter
        .printHexBinary(digest).toUpperCase();

      return myHash;
    } catch (Exception e) {
      return "";
    }
  }
}
