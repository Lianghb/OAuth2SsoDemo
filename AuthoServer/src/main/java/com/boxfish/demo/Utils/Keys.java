/*
* Copyright (c) 2015 boxfish.cn. All Rights Reserved.
*/
package com.boxfish.demo.Utils;

import org.bouncycastle.jcajce.provider.symmetric.ARC4;
import org.hibernate.annotations.SourceType;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPrivateKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with Intellij IDEA
 * Author: boxfish
 * Date: 16/1/6
 * Time: 23:19
 */
public class Keys {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    public static void main(String[] args) {
        Map<String, Object> keyMap;
        try {
            keyMap = initKey();
            String publicKey = getPublicKey(keyMap);
            System.out.println(publicKey);
            String privateKey = getPrivateKey(keyMap);
            System.out.println(privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        byte[] publicKey = key.getEncoded();
        return encryptBASE64(key.getEncoded());
    }

    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        byte[] privateKey = key.getEncoded();
        return encryptBASE64(key.getEncoded());
    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
}


class Test {
    static String publicKey;
    static String privateKey;
    static PublicKey publicKeyO;
    static PrivateKey privateKeyO;

    public Test() throws Exception {
        // TODO Auto-generated constructor stub
//        Map<String, Object> kMap = Keys.initKey();

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        privateKeyO = keyPair.getPrivate();
        publicKeyO = keyPair.getPublic();
        Test.privateKey = Base64Utils.encodeToString(privateKeyO.getEncoded());
        Test.publicKey  = Base64Utils.encodeToString(publicKeyO.getEncoded());
        System.err.println(String.format("publicKey\n\t%s\nprivateKey\n\t%s",Test.publicKey,Test.privateKey));
//        publicKey = Keys.getPublicKey(kMap);
//        privateKey = Keys.getPrivateKey(kMap);
//        Map<String, Object> keyMap = RSAUtils.genKeyPair();
//        publicKey = RSAUtils.getPublicKey(keyMap);
//        privateKey = RSAUtils.getPrivateKey(keyMap);

//        String tempDir = System.getProperty("java.io.tmpdir");
//        Assert.notNull(tempDir);
        String desktop = "/Users/boxfish/Desktop";
        Assert.hasText(desktop, "desktop err , required not null");
        System.err.println("desktop:" + desktop);

        String privateKeyPath = desktop + File.separator + "privateKey.txt";
        String publicKeyPath = desktop + File.separator + "publicKey.txt";
        // 保存密钥，名字分别为publicKey。txt 和privateKey。txt;
        PrintWriter pw1 = new PrintWriter(new FileOutputStream(publicKeyPath));
        PrintWriter pw2 = new PrintWriter(new FileOutputStream(privateKeyPath));
        pw1.print(publicKey);
        pw2.print(privateKey);
        pw1.close();
        pw2.close();
        System.out.println(String.format("Successfully save key to file. public key path : %s, private key path : %s"
                , publicKeyPath
                , privateKeyPath));
        // 从保存的目录读取刚才的保存的公钥，
//        String pubkey = readFile(publicKeyPath);// 读取的公钥内容；
        String data = "<a href=\"https://www.baidu.com/s?wd=%E5%85%AC%E9%92%A5%E5%8A%A0%E5%AF%86&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1d9mvn3PHRYmW-WmHF-PhmL0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EPW6znHRdPH03njmsPjDYPWRz\" target=\"_blank\" class=\"baidu-highlight\">公钥加密</a>";
        System.out.println(String.format("元数据:%s",data ));
        String encByPubKeyData = new String(encryptByPublicKey(data.getBytes("UTF-8"),Base64Utils.decodeFromString(Test.publicKey)));//.encryptByPublicKey(data.getBytes(),

        System.out.println("加密后的数据:%s" + encByPubKeyData);

        String decByPrivateKey = new String(decryptByPrivateKey(encByPubKeyData.getBytes("utf8"), Base64Utils.decodeFromString(Test.privateKey)));
        System.out.println("解密后的数据:%s" + decByPrivateKey);
//                pubkey);
//        //将加密数据base64后写入文件
//        writeFile("D:/Encfile.txt", Base64Utils.encode(encByPubKeyData));
//        // 加密后的文件保存在
//
//        String prikey = readFile("D:/privateKey.txt");// 从保存的目录读取刚才的保存的私钥，
//        String Encdata = readFile("D:/Encfile.txt");// 刚才加密的文件的内容;
//        byte[] encData = Base64Utils.decodeFromString(Encdata);
//        byte[] decByPriKeyData = RSAUtils.decryptByPrivateKey(encData, prikey);
//        // 解密后后的文件保存在D:/Decfile.txt
//        writeFile("D:/Decfile.txt", decByPriKeyData);
    }

//    private static String readFile(String filePath) throws Exception {
//        File inFile = new File(filePath);
//        long fileLen = inFile.length();
//        Reader reader = new FileReader(inFile);
//
//        char[] content = new char[(int) fileLen];
//        reader.read(content);
//        System.out.println("读取到的内容为：" + new String(content));
//        return new String(content);
//    }
//
//    private static void writeFile(String filePath, byte[] content)
//            throws Exception {
//        System.out.println("待写入文件的内容为：" + new String(content));
//        File outFile = new File(filePath);
//        OutputStream out = new FileOutputStream(outFile);
//        out.write(content);
//        if (out != null) out.close();
//    }

    /**
     * 利用公密钥进行加密
     *
     * @param data      需要加密的data
     * @param publicKey 公密钥
     * @return 加密后的密文
     */
    public byte[] encryptByPublicKey(byte[] data, byte[] publicKey) {
        byte[] encryptByteArray = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.ENCRYPT_MODE, Test.publicKeyO);
            X509EncodedKeySpec x509e = new X509EncodedKeySpec(publicKey);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, factory.generatePublic(x509e));
            encryptByteArray = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptByteArray;
    }

    /**
     * 利用私密进行密文解密
     *
     * @param data       密文
     * @param privateKey 私密钥
     * @return 解密后的明文
     */
    public byte[] decryptByPrivateKey(byte[] data, byte[] privateKey) {
        byte[] decryptArray = null;

        try {
            Cipher cipher = Cipher.getInstance("RSA");
            PKCS8EncodedKeySpec  s8ek  = new PKCS8EncodedKeySpec(privateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keyFactory.generatePrivate(s8ek));
//            cipher.init(Cipher.DECRYPT_MODE, Test.privateKeyO);
            decryptArray = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptArray;
    }


    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        new Test();
    }

}