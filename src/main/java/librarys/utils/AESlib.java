package librarys.utils;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class AESlib
{
    public static final String KEY_ALGORITHM = "AES";
    public static final String ECB_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    public static final String CBC_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    public static final String PLAIN_TEXT = "MANUTD is the greatest club in the world";
    public static final byte[] ivBytes = { 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

    public static String encrypt(String keydata, String passwd)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException
    {
        byte[] passwdBytes = passwd.getBytes();
        byte[] keyBytes = keydata.getBytes();

        if ((keyBytes.length != 16) && (keyBytes.length != 32)) {
            return "";
        }
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec k = new SecretKeySpec(keyBytes, "AES");
        cipher.init(1, k);

        return Base64.encodeBase64String(cipher.doFinal(passwdBytes));
    }

    public static String decrypt(String key, String data)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException
    {
        byte[] keyBytes = key.getBytes();
        byte[] dataBytes = Base64.decodeBase64(data);
        if ((keyBytes.length != 16) && (keyBytes.length != 32)) {
            return "";
        }
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec k = new SecretKeySpec(keyBytes, "AES");
        cipher.init(2, k);

        return new String(cipher.doFinal(dataBytes));
    }

    public static byte[] encryptCBC(byte[] keyBytes, byte[] dataBytes)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException
    {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, newKey, ivSpec);
        return cipher.doFinal(dataBytes);
    }

    public static byte[] decryptCBC(byte[] keyBytes, byte[] dataBytes)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException
    {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, newKey, ivSpec);
        return cipher.doFinal(dataBytes);
    }
}
