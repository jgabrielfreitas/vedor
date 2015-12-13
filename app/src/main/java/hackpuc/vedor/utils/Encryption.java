package hackpuc.vedor.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public class Encryption {

    public String encrypt(String secretKey, String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = message.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            String base64EncryptedString = new String(base64Bytes);

            return base64EncryptedString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(String secretKey, String encryptedText) {
        try {
            byte[] message = Base64.decodeBase64(encryptedText.getBytes("utf-8"));

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            return new String(plainText, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
