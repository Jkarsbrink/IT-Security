package rsa;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TheCrypt {
    String encrypt(String message, KeyPair key) {
        System.out.println("Message has been encrypted");
        return (new BigInteger(message.getBytes(StandardCharsets.UTF_8))).modPow(key.getKey(), key.getN()).toString();
    }

    String decrypt(String message, KeyPair key) {
        String msg = new String(message.getBytes(StandardCharsets.UTF_8));
        return new String((new BigInteger(msg)).modPow(key.getKey(), key.getN()).toByteArray());
    }

    void saveMsg(String fileName, String cryptMsg) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName + ".txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(cryptMsg);
            out.close();
            System.out.println("Saved message as " + fileName + ".txt");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    String getCrypted(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        String msg = in.readObject().toString();
        return msg;
    }

    String getMsg(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}