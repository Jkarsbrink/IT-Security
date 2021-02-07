package it.security;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class Main {

    public static void encryptDecrypt(String key, int cipherMode, File in, File out)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,InvalidAlgorithmParameterException, IOException {
        FileInputStream s = new FileInputStream(in);
        FileOutputStream o = new FileOutputStream(out);

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());

        SecretKeyFactory Sc = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = Sc.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES");

        byte[] ivBytes = new byte[8];
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        if (cipherMode == Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, SecureRandom.getInstance("1"));
            CipherInputStream CIS = new CipherInputStream(s, cipher);

        } else if (cipherMode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, SecureRandom.getInstance("1"));
            CipherOutputStream COS = new CipherOutputStream(o, cipher);
        }


    }

    public static void main(String[] args) {
        int bitLength = 4096;
        //generateKeys("Jocke", bitLength);
        RSA rsa = new RSA("Julius");
        String cipher = rsa.encrypt("Kom igen Julius!", rsa.getPublicKey());
        String message = rsa.decrypt(cipher, rsa.getPrivateKey());
        System.out.println(message);



        String key = "Hej";
        File text = new File("/Users/juliu/OneDrive/Skrivbord/Kyh/GitHub/it.security/cleartext.txt");
        File encrypted = new File("/Users/juliu/OneDrive/Skrivbord/Kyh/GitHub/it.security/encrypted.txt");
        File decrypted = new File("/Users/juliu/OneDrive/Skrivbord/Kyh/GitHub/it.security/decrypted.txt");

        try {
            encryptDecrypt("1234", Cipher.DECRYPT_MODE,encrypted,decrypted);
            System.out.println("Decryption complete");
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IOException | InvalidAlgorithmParameterException e)
        {
            e.printStackTrace();

        }
    }
}