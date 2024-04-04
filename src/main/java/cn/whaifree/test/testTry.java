package cn.whaifree.test;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 22:42
 * @注释
 */
public class testTry {

    @Test
    public void test() {
        int i = tryTest();
        System.out.println(i);
    }

    public int tryTest() {

        try {
            return 1;
        } catch (Exception e) {

        }finally {
            return 2;
        }
    }

}

class SymmetricEncryptionUtil {



    private static final String ALGORITHM = "AES";
    private static byte[] SECRET_KEY = null; // 替换为你自己的密钥，密钥长度必须符合算法要求

    public static byte[] encrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encryptedData);
    }

    public static void main(String[] args) throws Exception {
        SECRET_KEY = KeyGenerator.getInstance(ALGORITHM).generateKey().getEncoded();
        System.out.println(new String(SECRET_KEY));
        byte[] encrypt = SymmetricEncryptionUtil.encrypt("123456".getBytes());
        System.out.println(new String(encrypt));
        byte[] decrypt = SymmetricEncryptionUtil.decrypt(encrypt);
        System.out.println(new String(decrypt));

    }
}
