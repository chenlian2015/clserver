package com.cnd.greencube.web.base.util.encrypt;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class DES {
 
    private final static String DES = "DES";

     
    /**
     * Description ????????
     * @param data 
     * @param key  ???byte??
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }
 
    /**
     * Description ????????
     * @param data
     * @param key  ???byte??
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf,key.getBytes());
        return new String(bt);
    }
 
    /**
     * Description ????????
     * @param data
     * @param key  ???byte??
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // ????????????
        SecureRandom sr = new SecureRandom();
 
        // ?????????DESKeySpec??
        DESKeySpec dks = new DESKeySpec(key);
 
        // ??????????????DESKeySpec???SecretKey??
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher??????????
        Cipher cipher = Cipher.getInstance(DES);
 
        // ??????Cipher??
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
     
     
    /**
     * Description ????????
     * @param data
     * @param key  ???byte??
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // ????????????
        SecureRandom sr = new SecureRandom();
 
        // ?????????DESKeySpec??
        DESKeySpec dks = new DESKeySpec(key);
 
        // ??????????????DESKeySpec???SecretKey??
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher??????????
        Cipher cipher = Cipher.getInstance(DES);
 
        // ??????Cipher??
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
}

