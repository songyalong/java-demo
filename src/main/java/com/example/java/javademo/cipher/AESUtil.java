package com.example.java.javademo.cipher;

import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Author: songyalong
 * @Description: aes 加密 解密
 * @Modified By:
 */
public class AESUtil {
    private static final String KEY_ALGORITHM = "AES";
    //默认的加密算法
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String CIPHER_NOPADDING = "AES/CBC/NoPadding";

    public static String encrypt(String content, String password, String cipherModel){

        try {
            Cipher cipher = Cipher.getInstance(cipherModel);
            byte[] bytes = content.getBytes("utf-8");

//            IvParameterSpec ivSpec = new IvParameterSpec(password.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
            byte[] result = cipher.doFinal(bytes);
            return  Base64.encodeBase64String(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String  decrypt(String content, String password, String cipherModel) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance(cipherModel);
//            IvParameterSpec iv = new IvParameterSpec(password.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
            byte[] byte_content = new BASE64Decoder().decodeBuffer(content);
            byte[] bytes = cipher.doFinal(byte_content);
//            return bytes;
            return new String(bytes, "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static SecretKeySpec getSecretKey(final String password){
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            keyGenerator.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), "AES");
            return skeySpec;
//            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SecretKey getSecretKey2(final String password) throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //2.根据ecnodeRules规则初始化密钥生成器
        //生成一个128位的随机源,根据传入的字节数组
        SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        keygen.init(128, random);
        //3.产生原始对称密钥
        SecretKey original_key = keygen.generateKey();
        byte[] raw = original_key.getEncoded();
        //5.根据字节数组生成AES密钥
        SecretKey key = new SecretKeySpec(raw, "AES");
        return key;
    }

    public static void main(String[] args) throws Exception {

        String publicKey = "1234567890123456";

        String encrypt = encrypt(publicKey, publicKey, CIPHER_NOPADDING);
        System.out.println("encrypt = " + encrypt);

        String decrypt = decrypt(encrypt, publicKey, CIPHER_NOPADDING);

        System.out.println("decrypt = "+ decrypt);


    }

}
